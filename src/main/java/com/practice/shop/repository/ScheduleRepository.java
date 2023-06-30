package com.practice.shop.repository;

import com.practice.shop.model.schedule.Schedule;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Transactional
    @Modifying
    @Query("update Schedule s set s.isAvailable = true where s.id = ?1")
    int updateIsAvailableById(Long id);

    List<Schedule> findByIdTeacherAndDateTimeStartGreaterThanEqualAndDateTimeStartLessThanEqual(Long idTeacher, LocalDateTime dateTimeStart, LocalDateTime dateTimeStart1, Sort sort);

}
