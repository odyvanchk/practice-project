package com.practice.shop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UserRole {

    STUDENT(2),
    TEACHER(1);

    @Getter
    private final Integer roleNumber;

}
