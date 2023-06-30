package com.practice.shop.repository;

import com.practice.shop.model.BlackList;
import com.practice.shop.model.BlackListPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList, BlackListPK> {

}