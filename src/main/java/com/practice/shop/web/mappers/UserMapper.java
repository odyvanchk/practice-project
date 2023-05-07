package com.practice.shop.web.mappers;

import com.practice.shop.model.user.User;
import com.practice.shop.repository.UserHasRoleRepository;
import com.practice.shop.web.dto.UserDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", expression = "java(null)")
    UserDto userToUserDto(User user, @Context UserHasRoleRepository repository);

    @AfterMapping
    default void addRolesToDto(@MappingTarget UserDto target, @Context UserHasRoleRepository repository) {
        target.setStudent(repository.existsByUseridAndRoleId(target.getId(), 2));
        target.setTeacher(repository.existsByUseridAndRoleId(target.getId(), 1));
    }

    User userDtoToUser(UserDto userDto);

    @Mapping(target = "password", expression = "java(null)")
    void updateUserDto(@MappingTarget UserDto userDto, User user);

}
