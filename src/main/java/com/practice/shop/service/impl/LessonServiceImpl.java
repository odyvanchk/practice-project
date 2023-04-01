package com.practice.shop.service.impl;

import com.practice.shop.model.Schedule;
import com.practice.shop.model.exception.IllegalOperationException;
import com.practice.shop.model.lesson.Lesson;
import com.practice.shop.model.lesson.LessonId;
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
    public Lesson book(Long lessonScheduleId, Long userId) {
        Schedule lessonSchedule = scheduleRepository.getReferenceById(lessonScheduleId);
        if (lessonSchedule.getCurrentStudentsCount() >= lessonSchedule.getMaxStudentsCount()) {
            throw new IllegalOperationException("no available places");
        }
        Lesson newLesson = new Lesson();
        newLesson.setId(new LessonId());
        newLesson.setIdStudent(new User(userId));
        newLesson.setSchedule(new Schedule(lessonScheduleId));
        newLesson.setStatus(new LessonsStatus(0, "BOOKED"));
        return lessonRepository.save(newLesson);
    }
}
