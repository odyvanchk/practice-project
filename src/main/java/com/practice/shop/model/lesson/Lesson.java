package com.practice.shop.model.lesson;

import com.practice.shop.model.Schedule;
import com.practice.shop.model.user.User;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "lessons")
public class Lesson {

    @EmbeddedId
    private LessonId id;

    @MapsId("idLessonFromSchedule")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lesson_from_schedule")
    private Schedule schedule;

    @MapsId("idStudent")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_student", nullable = false)
    private User idStudent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status", nullable = false)
    private LessonsStatus status;

//    @Column(name = "max_students_count", nullable = false)
//    private Integer maxStudentsCount;

}