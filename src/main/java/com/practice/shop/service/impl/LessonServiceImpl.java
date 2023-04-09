package com.practice.shop.service.impl;

import com.practice.shop.model.Schedule;
import com.practice.shop.model.exception.IllegalOperationException;
import com.practice.shop.model.lesson.Lesson;
import com.practice.shop.model.lesson.LessonsStatus;
import com.practice.shop.model.user.User;
import com.practice.shop.repository.LessonRepository;
import com.practice.shop.repository.ScheduleRepository;
import com.practice.shop.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    @Transactional
    public Lesson book(Long lessonScheduleId, Long studentId) {
        Schedule lessonSchedule = scheduleRepository.getReferenceById(lessonScheduleId);
        if (!lessonSchedule.isAvailable()) {
            throw new IllegalOperationException("sorry, this lesson has already booked");
        }
        Lesson newLesson = new Lesson();
        newLesson.setIdStudent(new User(studentId));
        newLesson.setIdTeacher(new User(lessonSchedule.getIdTeacher()));
        newLesson.setDateTime(lessonSchedule.getDateTimeStart());
        newLesson.setStatus(LessonsStatus.BOOKED);
        lessonSchedule.setAvailable(false);
        scheduleRepository.save(lessonSchedule);
        return lessonRepository.save(newLesson);
    }

}
