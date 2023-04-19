package com.practice.shop.model;

import com.practice.shop.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;

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
    @JoinColumnOrFormula( column =
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id_user",
            insertable = false,
            updatable = false
    )
    )
    private User user;

    @MapsId("blockedUserId")
    @ManyToOne
    @JoinColumnOrFormula( column =
    @JoinColumn(
            name = "blocked_user_id",
            referencedColumnName = "id_teacher",
            insertable = false,
            updatable = false
    )
    )
    private TeachersDescription bannedUser;

}
