package com.practice.shop.web.controller;

import com.practice.shop.model.schedule.Schedule;
import com.practice.shop.service.ScheduleService;
import com.practice.shop.service.SearchLessonService;
import com.practice.shop.service.TeacherService;
import com.practice.shop.web.dto.ScheduleDto;
import com.practice.shop.web.dto.TeachersDescriptionDto;
import com.practice.shop.web.mappers.TeachersCriteriaMapper;
import com.practice.shop.web.mappers.TeachersDescriptionMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ermakovich Kseniya
 */
@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
public class TeacherScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping(value = "/teachers/{id}")
    public List<Schedule> save (@PathVariable Long id, @ModelAttribute ScheduleDto scheduleDto) {
        return scheduleService.save(id, scheduleDto);
    }

}
