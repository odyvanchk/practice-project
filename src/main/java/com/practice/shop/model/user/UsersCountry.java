package com.practice.shop.model.user;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "users_countries")
public class UsersCountry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_country", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

}