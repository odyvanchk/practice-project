package com.practice.shop.service;

import com.practice.shop.model.lesson.Lesson;
import java.util.List;

public interface LessonService {

    void cancelByTeacher(Long lessonId);

    void cancelByStudent(Long lessonId);

    List<Lesson> findFutureByTeacherId(Long id, int page);

    List<Lesson> findPastByTeacherId(Long id, int page);

    List<Lesson> findPastByStudentId(Long studentId, int page);

    List<Lesson> findFutureByStudentId(Long studentId, int page);

}
