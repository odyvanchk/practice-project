package com.practice.shop.web.mappers;

import com.practice.shop.model.TeachersDescription;
import com.practice.shop.web.dto.TeachersDescriptionDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface TeachersDescriptionMapper {

   // @Mapping(target = "schedules", expression = "java(null)")
    List<TeachersDescriptionDto> entityToDto(List<TeachersDescription> teachersDescriptionList);

    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "login", source = "user.login")
//    @Mapping(source = "blackLists", ignore = true)
    TeachersDescriptionDto entityToDto(TeachersDescription teachersDescription);

    TeachersDescription dtoToEntity(TeachersDescriptionDto teachersDescriptionDto);

    List<TeachersDescription> dtoToEntity(List<TeachersDescriptionDto> teachersDescriptionDtoList);
}
