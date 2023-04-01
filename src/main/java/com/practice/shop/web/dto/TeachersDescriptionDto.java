package com.practice.shop.web.dto;

import com.practice.shop.model.Schedule;
import com.practice.shop.model.user.TeachersPermanentPreference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class TeachersDescriptionDto extends UserDto {

    private Integer country;
    private Integer experience;
    private String description;
    private String photoRef;
    private MultipartFile image;
    private Boolean isNative;
    private Double mark;
    private BigDecimal defaultPrice;
    private Integer defaultStudentCount;
    private Set<Schedule> schedules;
    private Set<TeachersPermanentPreference> teachersPermanentPreferences;

}
