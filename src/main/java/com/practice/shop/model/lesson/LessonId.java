package com.practice.shop.model.lesson;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LessonId implements Serializable {

    private static final long serialVersionUID = -4941835432219263054L;

    @Column(name = "id_lesson_from_schedule", nullable = false)
    private Long idLessonFromSchedule;

    @Column(name = "id_student", nullable = false)
    private Long idStudent;


}