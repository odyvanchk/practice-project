package com.practice.shop.model;

import java.util.List;
import lombok.Data;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class TchDescriptionResultList {

    private List<TeachersDescription> teachersDescriptions;
    private Long totalCount;
    private Long currentPage;
    private Integer pageSize;

}
