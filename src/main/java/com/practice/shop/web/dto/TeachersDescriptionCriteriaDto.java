package com.practice.shop.web.dto;

import com.practice.shop.model.Language;
import com.practice.shop.model.LanguageLevel;
import com.practice.shop.model.user.UsersCountry;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeachersDescriptionCriteriaDto {

    private UsersCountry country;
    private Integer experienceFrom;
    private Integer experienceTo;
    private Boolean isNative;
    private Double markFrom;
    private Double markTo;
    private BigDecimal defaultPriceFrom;
    private BigDecimal defaultPriceTo;
    private Language language;
    private LanguageLevel levelFrom;

}
