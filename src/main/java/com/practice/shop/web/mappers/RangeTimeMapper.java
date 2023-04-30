package com.practice.shop.web.mappers;

import com.practice.shop.model.RangeTime;
import com.practice.shop.web.dto.TimeRangeDto;

/**
 * @author Ermakovich Kseniya
 */
public interface RangeTimeMapper {

    RangeTime toEntity(TimeRangeDto rangeDto);

}
