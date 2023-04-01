package com.practice.shop.web.mappers;

import com.practice.shop.model.TeachersDescriptionCriteria;
import com.practice.shop.web.dto.TeachersDescriptionCriteriaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeachersCriteriaMapper {

    TeachersDescriptionCriteria dtoToEntity(TeachersDescriptionCriteriaDto criteriaDto);

}
