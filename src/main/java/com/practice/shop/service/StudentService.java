package com.practice.shop.service;

import com.practice.shop.model.lesson.Lesson;
import java.util.List;

/**
 * @author Ermakovich Kseniya
 */
public interface StudentService {

    void ban(Long teacherId);

    List<Lesson> findFutureLessons(Long studentId, int page);

    List<Lesson> findPastLessons(Long studentId, int page);

    Lesson addNote(Long lessonId, String note);

}
