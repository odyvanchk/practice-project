package com.practice.shop.model;

import com.practice.shop.model.lesson.LessonsStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;


@Getter
@Setter
@Entity
@Table(name = "schedules")
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lesson", nullable = false)
    private Long id;

    @Column(name = "id_teacher", nullable = false)
    private Integer idTeacher;

    @Column(name = "date_time_start", nullable = false)
    private Instant dateTimeStart;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "status", nullable = false)
    private LessonsStatus status;

//    @Column(name = "topic", length = 500)
//    private String topic;

    @Column(name = "current_students_count", nullable = false)
    private Integer currentStudentsCount;

    @Column(name = "max_students_count", nullable = false)
    private Integer maxStudentsCount;

    @Column(name = "date_time_finish")
    private Instant dateTimeFinish;

    public Schedule(Long lessonScheduleId) {
        this.id = lessonScheduleId;
    }
}