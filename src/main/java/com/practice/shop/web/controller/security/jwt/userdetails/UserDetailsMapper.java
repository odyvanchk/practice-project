package com.practice.shop.web.controller.security.jwt.userdetails;

import com.practice.shop.model.user.User;
import com.practice.shop.repository.UserHasRoleRepository;
import java.util.Collection;
import java.util.LinkedList;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDetailsMapper {
    private final UserHasRoleRepository userHasRoleRepository;

    public CustomUserDetails toUserDetails(User user) {
        CustomUserDetails c =  new CustomUserDetails();
        c.setEmail(user.getEmail());
        c.setPassword(user.getPassword());
        c.setId(user.getId());
        c.setGrantedAuthorities(makeGrantedAuthorityList(user));
        return c;
    }

    private Collection<SimpleGrantedAuthority> makeGrantedAuthorityList(User userDto) {
        Collection<SimpleGrantedAuthority> grantedAuthorities = new LinkedList<>();
        boolean isStudent = userHasRoleRepository.existsByUseridAndRoleId(userDto.getId(), 2);
        boolean isTeacher = userHasRoleRepository.existsByUseridAndRoleId(userDto.getId(), 1);

        if (isStudent) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        }
        if (isTeacher) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
        }
        return grantedAuthorities;
    }
}
