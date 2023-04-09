package com.practice.shop.repository;

import com.practice.shop.model.lesson.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
