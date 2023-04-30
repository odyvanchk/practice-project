package com.practice.shop.web.dto;

import com.practice.shop.model.lesson.LessonsStatus;
import com.practice.shop.model.schedule.Schedule;
import lombok.Data;

@Data
public class LessonDto {

    private Long id;
    private Schedule schedule;
    private Long idStudent;
    private Long idTeacher;
    private LessonsStatus status;

}
