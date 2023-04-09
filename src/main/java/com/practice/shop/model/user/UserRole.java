package com.practice.shop.model.user;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_roles")
@Data
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_role", nullable = false)
    private Integer id;

    @Column(name = "user_role", nullable = false, length = 60)
    private String name;

}