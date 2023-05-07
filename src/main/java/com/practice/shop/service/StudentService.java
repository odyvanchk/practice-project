package com.practice.shop.service;

import com.practice.shop.model.lesson.Lesson;
import com.practice.shop.model.lesson.LessonResultList;

/**
 * @author Ermakovich Kseniya
 */
public interface StudentService {

    void ban(Long teacherId);

    LessonResultList findFutureLessons(Long studentId, int page);

    LessonResultList findPastLessons(Long studentId, int page);

    Lesson addNote(Long lessonId, String note);

}
