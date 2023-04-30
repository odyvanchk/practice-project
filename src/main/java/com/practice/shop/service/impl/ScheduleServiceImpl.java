package com.practice.shop.service.impl;

import com.practice.shop.model.RangeTime;
import com.practice.shop.model.exception.IllegalOperationException;
import com.practice.shop.model.lesson.Lesson;
import com.practice.shop.model.lesson.LessonsStatus;
import com.practice.shop.model.schedule.Command;
import com.practice.shop.model.schedule.Schedule;
import com.practice.shop.repository.LessonRepository;
import com.practice.shop.repository.ScheduleRepository;
import com.practice.shop.service.ScheduleService;
import com.practice.shop.web.controller.security.userdetails.CustomUserDetails;
import com.practice.shop.web.dto.ScheduleDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final LessonRepository lessonRepository;

    @Override
    public List<Schedule> save(Long id, ScheduleDto scheduleDto) {
        List<Schedule> schedules = new ArrayList<>();
        scheduleDto.times()
                .stream()
                .filter(slot -> slot.getCommand().equals(Command.SAVE))
                .forEach(slot -> {
                    Schedule schedule = new Schedule();
                    schedule.setIdTeacher(id);
                    schedule.setAvailable(true);
                    schedule.setDateTimeStart(slot.getTime());
                    schedules.add(schedule);
                        });
        scheduleDto.times()
                .stream()
                .filter(slot -> slot.getCommand().equals(Command.DELETE))
                .forEach(slot -> scheduleRepository.deleteById(slot.getId()));

        return scheduleRepository.saveAll(schedules);
    }

    @Override
    public List<Schedule> get(Long id, RangeTime range) {
        return scheduleRepository.findByIdTeacherAndDateTimeStartGreaterThanEqualAndDateTimeStartLessThanEqual(
                id,
                range.getStart(),
                range.getFinish(), Sort.by("dateTimeStart"));
    }

    @Override
    @Transactional
    public List<Lesson> book(Long teacherId, List<Long> scheduleArray) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        ArrayList<Lesson> lessons = new ArrayList<>();
        ArrayList<Schedule> schedules = new ArrayList<>();
        scheduleArray.forEach(id -> {
            Schedule lessonSchedule = scheduleRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("schedule doesn't exist"));
            if (!lessonSchedule.isAvailable()) {
                throw new IllegalOperationException("sorry, this lesson has already booked");
            }
            Lesson newLesson = new Lesson();
            newLesson.setIdStudent(userDetails.getId());
            newLesson.setIdTeacher(lessonSchedule.getIdTeacher());
            newLesson.setDateTime(lessonSchedule.getDateTimeStart());
            newLesson.setStatus(LessonsStatus.BOOKED);
            newLesson.setSchedule(lessonSchedule);
            lessonSchedule.setAvailable(false);
            schedules.add(lessonSchedule);
            lessons.add(newLesson);
        });

        scheduleRepository.saveAll(schedules);
        return lessonRepository.saveAll(lessons);
    }

}
