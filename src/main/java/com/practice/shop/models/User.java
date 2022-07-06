package com.practice.shop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private int id;

    @Column(name = "password_hash", nullable = false)
    private String password;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String email;

    @Column()
    private boolean isEmailConfirm;

}
