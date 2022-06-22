package com.practice.shop.controllers.security;

import com.practice.shop.DTO.UserDto;
import com.practice.shop.services.UserService;
import com.practice.shop.services.exception.UserNotFoundedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService  implements UserDetailsService {
    private UserService userService;
    private UserDetailsMapper userDetailsMapper;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            UserDto user = userService.findByEmail(email);
            return userDetailsMapper.toUserDetails(user);
        }
        catch (UsernameNotFoundException ex){
            throw new UserNotFoundedException(email);
        }
    }
}
