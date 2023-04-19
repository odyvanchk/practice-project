package com.practice.shop.model;

import com.practice.shop.model.user.UsersCountry;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
public class TeachersDescriptionCriteria {

    private Set<UsersCountry> countries;
    private Integer experienceFrom;
    private Integer experienceTo;
    private String description;
    private Boolean isNative;
    private Double markFrom;
    private Double markTo;
    private BigDecimal defaultPriceFrom;
    private BigDecimal defaultPriceTo;
    private Integer weekDay;
    private LocalTime start;
    private Language language;
    private LanguageLevel levelFrom;

}
