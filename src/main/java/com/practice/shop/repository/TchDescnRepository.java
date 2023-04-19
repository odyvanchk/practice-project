package com.practice.shop.repository;

import com.practice.shop.model.TeachersDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TchDescnRepository extends JpaRepository<TeachersDescription, Long> {

}
