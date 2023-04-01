package com.practice.shop.repository;

import com.practice.shop.model.lesson.Lesson;
import com.practice.shop.model.lesson.LessonId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, LessonId> {
}
