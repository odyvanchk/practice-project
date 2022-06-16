package com.practice.shop.services.mappers;

import com.practice.shop.DAO.UserHasRoleRepository;
import com.practice.shop.DTO.UserDto;
import com.practice.shop.models.User;
import com.practice.shop.models.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDtoMapper implements Mapper<User, UserDto> {
    private final UserHasRoleRepository userHasRoleRepository;

    @Override
    public User toEntity(UserDto dtoEntity) {
        if (dtoEntity == null) {
            return null;
        }
        return new User(dtoEntity.getId(), dtoEntity.getPassword(), dtoEntity.getLogin(), dtoEntity.getEmail());
    }

    @Override
    public UserDto toDTO(User entity) {
        if (entity == null) {
            return null;
        }
        boolean isStudent = userHasRoleRepository.existsByUseridAndRoleId(entity.getId(), UserRole.STUDENT.getRoleNumber());
        boolean isTeacher = userHasRoleRepository.existsByUseridAndRoleId(entity.getId(), UserRole.TEACHER.getRoleNumber());
        return new UserDto(entity.getId(), entity.getPassword(), entity.getLogin(), entity.getEmail(), isTeacher, isStudent);
    }
}