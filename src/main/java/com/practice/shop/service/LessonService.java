package com.practice.shop.service;

import com.practice.shop.model.lesson.Lesson;
import com.practice.shop.model.lesson.LessonResultList;

public interface LessonService {

    void cancelByTeacher(Long lessonId);

    void cancelByStudent(Long lessonId);

    LessonResultList findFutureByTeacherId(Long id, int page);

    LessonResultList findPastByTeacherId(Long id, int page);

    LessonResultList findPastByStudentId(Long studentId, int page);

    LessonResultList findFutureByStudentId(Long studentId, int page);

    Lesson findById(Long lessonId);

    Lesson save(Lesson lesson);
}
