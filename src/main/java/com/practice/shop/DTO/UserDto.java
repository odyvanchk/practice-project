package com.practice.shop.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int id;

    @Size(min = 8, max = 20, message = "password should contain 8-20 characters" )
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "password should contain a-z, A-Z, 0-9")
    private String password;

    @Size(min = 4, max = 15, message = "login should contain 4-15 characters" )
    private String login;

    @Pattern(regexp = ".+@.+\\.[a-zA-Z0-9]+", message = "email should contain @ and .")
    private String email;

    @JsonProperty("isTeacher")
    private boolean isTeacher;

    @JsonProperty("isStudent")
    private boolean isStudent;

    private String fingerprint;

    public UserDto(String password, String login, String email, boolean isTeacher, boolean isStudent) {
        this.password = password;
        this.login = login;
        this.email = email;
        this.isTeacher = isTeacher;
        this.isStudent = isStudent;
    }
}
