package com.practice.shop.model.lesson;

import com.practice.shop.model.schedule.Schedule;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;


@Data
@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @Column(name = "id_lesson", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @MapsId("idTeacher")
//    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_teacher", nullable = false)
    private Long idTeacher;

//    @MapsId("idStudent")
//    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_student", nullable = false)
    private Long idStudent;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_schedule")
    private Schedule schedule;

    @Enumerated(EnumType.STRING)
    private LessonsStatus status;

    private String note;

}