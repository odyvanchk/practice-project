package com.practice.shop.controllers.security.userdetails;

import com.practice.shop.DTO.UserDto;
import com.practice.shop.services.AuthService;
import com.practice.shop.services.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomUserDetailsService  implements UserDetailsService {

    private AuthService authService;
    private UserDetailsMapper userDetailsMapper;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            UserDto user = authService.findByEmail(email);
            return userDetailsMapper.toUserDetails(user);
        }
        catch (UsernameNotFoundException ex){
            throw new UserNotFoundException(email);
        }
    }
}
