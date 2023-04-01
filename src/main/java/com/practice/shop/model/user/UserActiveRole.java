package com.practice.shop.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_has_role")
@IdClass(UserActiveRole.UserRoleId.class)
public class UserActiveRole {

    @Id
    @Column(name = "id_user", nullable = false)
    private Long userid;

    @Id
    @Column(name = "id_role", nullable = false)
    private Integer roleId;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRoleId implements Serializable {

            private Integer userid;
            private Integer roleId;
    }
}
