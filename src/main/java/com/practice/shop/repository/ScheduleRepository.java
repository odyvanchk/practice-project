package com.practice.shop.repository;

import com.practice.shop.model.schedule.Schedule;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByIdTeacherAndDateTimeStartGreaterThanEqualAndDateTimeStartLessThanEqual(Long idTeacher, LocalDateTime dateTimeStart, LocalDateTime dateTimeStart1);

    @Transactional
    @Modifying
    @Query("update Schedule s set s.isAvailable = ?1 where s.isAvailable = false")
    int updateIsAvailableByIsAvailableFalse(boolean isAvailable);
}
