package com.practice.shop.model.lesson;

import java.util.List;
import lombok.Data;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class LessonResultList {

    private List<Lesson> lessons;
    private Long totalCount;
    private Long currentPage;
    private Integer pageSize;

}
