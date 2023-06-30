package com.practice.shop.web.controller;

import com.practice.shop.model.RangeTime;
import com.practice.shop.model.lesson.Lesson;
import com.practice.shop.model.schedule.Schedule;
import com.practice.shop.service.ScheduleService;
import com.practice.shop.web.dto.LessonDto;
import com.practice.shop.web.dto.ScheduleDto;
import com.practice.shop.web.dto.TimeRangeDto;
import com.practice.shop.web.mappers.LessonMapper;
import com.practice.shop.web.mappers.RangeTimeMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private final RangeTimeMapper mapper;
    private final LessonMapper lessonMapper;

    @PostMapping(value = "/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public List<Schedule> save (@PathVariable Long id, @RequestBody ScheduleDto scheduleDto) {
        return scheduleService.save(id, scheduleDto);
    }

    @PostMapping(value = "/{teacherId}/book")
    @PreAuthorize("hasRole('STUDENT')")
    public List<LessonDto> book (@PathVariable Long teacherId, @RequestBody ArrayList<Long> scheduleArray) {
        List<Lesson> lessons = scheduleService.book(teacherId, scheduleArray);
        return lessonMapper.toDto(lessons);
    }

    @GetMapping(value = "/{id}")
    public List<Schedule> get (@PathVariable Long id, @ModelAttribute TimeRangeDto rangeDto) {
        RangeTime rangeTime = mapper.toEntity(rangeDto);
        return scheduleService.get(id, rangeTime);
    }

}
