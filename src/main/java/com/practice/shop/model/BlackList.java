package com.practice.shop.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    public BlackList(BlackListPK pk) {
        this.pk = pk;
    }

}
