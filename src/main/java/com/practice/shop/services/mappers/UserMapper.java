package com.practice.shop.services.mappers;

import com.practice.shop.DAO.UserHasRoleRepository;
import com.practice.shop.DTO.UserDto;
import com.practice.shop.models.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

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
