package com.practice.shop.model.lesson;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "lessons_statuses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonsStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lesson_status", nullable = false)
    private Integer id;

    @Column(name = "lesson_status", nullable = false, length = 60)
    private String lessonStatus;

}