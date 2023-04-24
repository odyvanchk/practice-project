package com.practice.shop.service.impl;

import com.practice.shop.model.RangeTime;
import com.practice.shop.model.schedule.Command;
import com.practice.shop.model.schedule.Schedule;
import com.practice.shop.repository.ScheduleRepository;
import com.practice.shop.service.ScheduleService;
import com.practice.shop.web.dto.ScheduleDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> save(Long id, ScheduleDto scheduleDto) {
        List<Schedule> schedules = new ArrayList<>();
        scheduleDto.times()
                .stream()
                .filter(slot -> slot.getCommand().equals(Command.SAVE))
                .forEach(slot -> {
                    Schedule schedule = new Schedule();
                    schedule.setIdTeacher(id);
                    schedule.setAvailable(true);
                    schedule.setDateTimeStart(slot.getTime());
                    schedules.add(schedule);
                        });
        scheduleDto.times()
                .stream()
                .filter(slot -> slot.getCommand().equals(Command.DELETE))
                .forEach(slot -> scheduleRepository.deleteById(slot.getId()));

        return scheduleRepository.saveAll(schedules);
    }

    @Override
    public List<Schedule> get(Long id, RangeTime range) {
        return scheduleRepository.findByIdTeacherAndDateTimeStartGreaterThanEqualAndDateTimeStartLessThanEqual(
                id,
                range.getStart(),
                range.getFinish());
    }

}
