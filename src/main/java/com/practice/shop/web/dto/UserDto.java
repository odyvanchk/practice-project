package com.practice.shop.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.practice.shop.web.dto.group.onSearchTeacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @Null(groups = onSearchTeacher.class)
    @Size(min = 8, max = 20, message = "password should contain 8-20 characters" )
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "password should contain a-z, A-Z, 0-9")
    private String password;

    @Null(groups = onSearchTeacher.class)
    @Size(min = 4, max = 15, message = "login should contain 4-15 characters" )
    private String login;

    @Pattern(regexp = ".+@.+\\.[a-zA-Z0-9]+", message = "email should contain @ and .")
    private String email;

    @JsonProperty("isTeacher")
    private boolean isTeacher;

    @JsonProperty("isStudent")
    private boolean isStudent;

    @Null(groups = onSearchTeacher.class)
    private String fingerprint;

}
