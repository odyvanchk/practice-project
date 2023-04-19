package com.practice.shop.service.impl;

import com.practice.shop.model.BlackList;
import com.practice.shop.model.BlackListPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList, BlackListPK> {

    boolean existsByPk_UserId(Long userId);

}