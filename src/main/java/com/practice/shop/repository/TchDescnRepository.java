package com.practice.shop.repository;

import com.practice.shop.model.TeachersDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TchDescnRepository extends JpaRepository<TeachersDescription, Long> {

    @Query(value = "call public.calculate_avg_mark(:teacher_id,:new_mark)", nativeQuery = true)
    void calculateMark(@Param("teacher_id")int teacherId, @Param("new_mark")float mark);

}
