package com.practice.shop.web.dto;

import com.practice.shop.model.Language;
import com.practice.shop.model.Schedule;
import com.practice.shop.model.user.UsersCountry;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class TeachersDescriptionDto extends UserDto {

    private UsersCountry country;
    private Integer experience;
    private String description;
    private String photoRef;
    private MultipartFile image;
    private Boolean isNative;
    private Double mark;
    private BigDecimal defaultPrice;
    private Integer defaultStudentCount;
    private Set<Language> languages;
    private Set<Schedule> schedules;

}
