package com.practice.shop.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;


@Data
@EqualsAndHashCode
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

    @Size(min = 4, max = 15, message = "login should contain 4-15 characters" )
    @Column(nullable = false)
    private String login;

    @Pattern(regexp = ".+@.+\\.[a-zA-Z0-9]+", message = "email should contain @ and .")
    @Column(nullable = false)
    private String email;

}
