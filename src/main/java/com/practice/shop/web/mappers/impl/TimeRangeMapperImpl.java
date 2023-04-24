package com.practice.shop.web.mappers.impl;

import com.practice.shop.model.RangeTime;
import com.practice.shop.web.dto.TimeRangeDto;
import com.practice.shop.web.mappers.RangeTimeMapper;
import java.time.Instant;
import java.time.ZoneId;
import org.springframework.stereotype.Component;

/**
 * @author Ermakovich Kseniya
 */
@Component
public class TimeRangeMapperImpl implements RangeTimeMapper {


    @Override
    public RangeTime toEntity(TimeRangeDto rangeDto) {
        RangeTime rangeTime = new RangeTime();
        rangeTime.setStart(Instant.ofEpochMilli(Long.parseLong(rangeDto.start())).atZone(ZoneId.of("UTC")).toLocalDateTime());
        rangeTime.setFinish(Instant.ofEpochMilli(Long.parseLong(rangeDto.finish())).atZone(ZoneId.of("UTC")).toLocalDateTime());
        return rangeTime;
    }
}
