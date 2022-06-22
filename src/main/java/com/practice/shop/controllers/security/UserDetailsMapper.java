package com.practice.shop.controllers.security;

import com.practice.shop.DAO.UserHasRoleRepository;
import com.practice.shop.DTO.UserDto;
import com.practice.shop.models.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;

@Component
@AllArgsConstructor
public class UserDetailsMapper {
    private final UserHasRoleRepository userHasRoleRepository;

    public UserDetails toUserDetails(UserDto userDto) {
        CustomUserDetails c =  new CustomUserDetails();
        c.setEmail(userDto.getEmail());
        c.setPassword(userDto.getPassword());
        c.setGrantedAuthorities(makeGrantedAuthorityList(userDto));

        return c;
    }

    private Collection<SimpleGrantedAuthority> makeGrantedAuthorityList(UserDto userDto) {
        Collection<SimpleGrantedAuthority> grantedAuthorities = new LinkedList<>();
        boolean isStudent = userHasRoleRepository.existsByUseridAndRoleId(userDto.getId(), UserRole.STUDENT.getRoleNumber());
        boolean isTeacher = userHasRoleRepository.existsByUseridAndRoleId(userDto.getId(), UserRole.TEACHER.getRoleNumber());

        if (isStudent) {
            grantedAuthorities.add(new SimpleGrantedAuthority("STUDENT"));
        }
        if (isTeacher) {
            grantedAuthorities.add(new SimpleGrantedAuthority("TEACHER"));
        }
        return grantedAuthorities;
    }
}
