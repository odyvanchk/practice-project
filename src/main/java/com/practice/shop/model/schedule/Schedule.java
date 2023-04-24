package com.practice.shop.model.schedule;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
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
    @Column(name = "id_schedule", nullable = false)
    private Long id;

    @Column(name = "id_teacher", nullable = false)
    private Long idTeacher;

    @Column(name = "time_start", nullable = false)
    private LocalDateTime dateTimeStart;

    @JoinColumn(name = "is_available", nullable = false)
    private boolean isAvailable;

}