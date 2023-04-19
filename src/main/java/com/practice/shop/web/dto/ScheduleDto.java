package com.practice.shop.web.dto;

import com.practice.shop.model.schedule.Slot;
import java.util.List;

/**
 * @author Ermakovich Kseniya
 */
public record ScheduleDto (

        Long id,
        List<Slot> times

) {}
