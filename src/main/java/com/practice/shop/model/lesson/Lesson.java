package com.practice.shop.model.lesson;

import com.practice.shop.model.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;


@Data
@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    private Long id;

    @MapsId("idTeacher")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_teacher", nullable = false)
    private User idTeacher;

    @MapsId("idStudent")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_student", nullable = false)
    private User idStudent;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    private LessonsStatus status;

    private String note;

}