package com.practice.shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


/**
 * @author Ermakovich Kseniya
 */
@Embeddable
public class BlackListPK {

    @Column(name = "user_id")
    protected Long userId;

    @Column(name = "blocked_user_id")
    protected Long blockedUserId;

    public BlackListPK() {}

    public BlackListPK(Long userId, Long blockedUserId) {
        this.userId = userId;
        this.blockedUserId = blockedUserId;
    }

}
