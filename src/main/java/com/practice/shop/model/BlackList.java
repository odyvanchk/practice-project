package com.practice.shop.model;

import com.practice.shop.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ermakovich Kseniya
 */
@Getter
@Setter
@Entity
@Table(name = "black_lists")
@NoArgsConstructor
public class BlackList {

    @EmbeddedId
    private BlackListPK pk;

    @MapsId("userId")
    @ManyToOne
    private User user;

    @MapsId("blockedUserId")
    @ManyToOne
    private User bannedUser;

}
