package com.practice.shop.model.schedule;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class Slot {

    private Long id;
    private LocalDateTime time;
    private Command command;

}
