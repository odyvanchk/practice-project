package com.practice.shop.service;

import com.practice.shop.model.lesson.Lesson;

public interface LessonService {

    Lesson book(Long lessonScheduleId, Long userId);

}
