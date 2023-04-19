package com.practice.shop.web.dto;

import com.practice.shop.model.schedule.Schedule;
import com.practice.shop.model.lesson.LessonsStatus;
import com.practice.shop.model.user.User;
import lombok.Data;

@Data
public class LessonDto {

    private Long id;
    private Schedule schedule;
    private User idStudent;
    private LessonsStatus status;

}
