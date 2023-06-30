package com.practice.shop.model.lesson;

import com.practice.shop.model.schedule.Schedule;
import com.practice.shop.model.user.User;
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

//    @JoinColumn(name = "id_teacher", nullable = false)
//    private Long idTeacher;
//
//    @JoinColumn(name = "id_student", nullable = false)
//    private Long idStudent;

    @ManyToOne
    @JoinColumn(name = "id_teacher")
    private User teacher;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private User student;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_schedule")
    private Schedule schedule;

    @Enumerated(EnumType.STRING)
    private LessonsStatus status;

    private String note;

}