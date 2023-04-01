package com.practice.shop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeachersDescriptionCriteriaDto {

    private Set<Integer> countryIdSet;

    private Integer experienceFrom;

    private Integer experienceTo;

    private String description;

    private String photoRef;

    private Boolean isNative;

    private Double markFrom;

    private Double markTo;

    private BigDecimal defaultPriceFrom;

    private BigDecimal defaultPriceTo;

    private Integer weekDay;

    private LocalTime start;



    //private Set<LocalDateTime> dateTimeSet;






}
