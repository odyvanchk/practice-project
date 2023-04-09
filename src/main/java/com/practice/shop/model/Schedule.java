package com.practice.shop.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "teachers_schedules")
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lesson", nullable = false)
    private Long id;

    @Column(name = "id_teacher", nullable = false)
    private Long idTeacher;

    @Column(name = "date_time_start", nullable = false)
    private LocalDateTime dateTimeStart;

    @JoinColumn(name = "is_available", nullable = false)
    private boolean isAvailable;

}