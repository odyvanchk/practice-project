package com.practice.shop.model;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class RangeTime {

    private LocalDateTime start;
    private LocalDateTime finish;

}
