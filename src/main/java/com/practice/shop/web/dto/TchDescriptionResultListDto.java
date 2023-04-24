package com.practice.shop.web.dto;

import java.util.List;

/**
 * @author Ermakovich Kseniya
 */
public record TchDescriptionResultListDto (

        List<TeachersDescriptionDto> teachersDescriptions,
        Long totalCount,
        Long currentPage,
        Integer pageSize

) { }
