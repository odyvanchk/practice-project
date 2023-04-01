package com.practice.shop.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_user", nullable = false)
    private Long id;

    @Column(name = "password_hash", nullable = false)
    private String password;

    @Size(min = 4, max = 15, message = "login should contain 4-15 characters" )
    @Column(nullable = false)
    private String login;

    @Pattern(regexp = ".+@.+\\.[a-zA-Z0-9]+", message = "email should contain @ and .")
    @Column(nullable = false)
    private String email;

    @Column()
    private boolean isEmailConfirm;


    @ManyToMany
    @JoinTable(name = "user_has_role",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<UserRole> userRoles = new LinkedHashSet<>();

    public User(Long userId) {
        this.id = userId;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }


//    @ManyToMany
//    @JoinTable(
//            name = "user_has_role",
//            joinColumns = @JoinColumn(name = "id_user"),
//            inverseJoinColumns = @JoinColumn(name = "id_role")
//    )
//    private Set<UsersRole> userRoles;

}
