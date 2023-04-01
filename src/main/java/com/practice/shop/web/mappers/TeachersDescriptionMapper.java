package com.practice.shop.web.mappers;

import com.practice.shop.model.user.TeachersDescription;
import com.practice.shop.web.dto.TeachersDescriptionDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface TeachersDescriptionMapper {

   // @Mapping(target = "schedules", expression = "java(null)")
    List<TeachersDescriptionDto> entityToDto(List<TeachersDescription> teachersDescriptionList);

    TeachersDescriptionDto entityToDto(TeachersDescription teachersDescription);

    TeachersDescription dtoToEntity(TeachersDescriptionDto teachersDescriptionDto);

    List<TeachersDescription> dtoToEntity(List<TeachersDescriptionDto> teachersDescriptionDtoList);
}
