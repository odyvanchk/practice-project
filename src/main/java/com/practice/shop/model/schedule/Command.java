package com.practice.shop.model.schedule;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author Ermakovich Kseniya
 */
public enum Command {

    SAVE,
    DELETE,
    CHANGE_STATUS;

    @JsonCreator
    public static Command forValue(String value) {
        return Command.values()[Integer.parseInt(value)];
    }

}
