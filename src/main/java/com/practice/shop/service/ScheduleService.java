package com.practice.shop.service;

import com.practice.shop.model.RangeTime;
import com.practice.shop.model.schedule.Schedule;
import com.practice.shop.web.dto.ScheduleDto;
import java.util.List;


/**
 * @author Ermakovich Kseniya
 */
public interface ScheduleService {

    List<Schedule> save(Long id, ScheduleDto scheduleDto);

    List<Schedule> get(Long id, RangeTime rangeDto);

}
