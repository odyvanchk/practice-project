package com.practice.shop.web.controller;

import com.practice.shop.model.RangeTime;
import com.practice.shop.model.schedule.Schedule;
import com.practice.shop.service.ScheduleService;
import com.practice.shop.web.dto.ScheduleDto;
import com.practice.shop.web.dto.TimeRangeDto;
import com.practice.shop.web.mappers.RangeTimeMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final RangeTimeMapper mapper;

    @PostMapping(value = "/{id}")
    public List<Schedule> save (@PathVariable Long id, @ModelAttribute ScheduleDto scheduleDto) {
        return scheduleService.save(id, scheduleDto);
    }

    @GetMapping(value = "/{id}")
    public List<Schedule> get (@PathVariable Long id, @ModelAttribute TimeRangeDto rangeDto) {
        RangeTime rangeTime = mapper.toEntity(rangeDto);
        return scheduleService.get(id, rangeTime);
    }

}
