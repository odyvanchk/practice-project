package com.practice.shop.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "teachers_permanent_preferences")
public class TeachersPermanentPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_schedule", nullable = false)
    private Integer id;

    @Column(name = "id_teacher")
    private Integer idTeacher;

    @Column(name = "week_day", nullable = false)
    private Integer weekDay;

    @Column(name = "time_start", nullable = false)
    private LocalTime timeStart;

    @Override
    public String toString() {
        return "TeachersPermanentPreference{" +
                "id=" + id +
                ", idTeacher=" + idTeacher +
                ", weekDay=" + weekDay +
                ", timeStart=" + timeStart +
                '}';
    }
}