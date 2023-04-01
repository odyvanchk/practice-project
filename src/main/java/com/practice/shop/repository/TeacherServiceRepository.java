package com.practice.shop.repository;

import com.practice.shop.model.user.TeachersDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherServiceRepository extends JpaRepository<TeachersDescription, Long> {

}
