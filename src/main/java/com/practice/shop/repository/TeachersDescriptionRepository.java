package com.practice.shop.repository;

import com.practice.shop.model.user.TeachersDescription;
import com.practice.shop.model.user.TeachersPermanentPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TeachersDescriptionRepository extends JpaRepository<TeachersDescription, Integer>, JpaSpecificationExecutor<TeachersDescription> {
    Optional<TeachersDescription> findByDefaultStudentCount(Integer defaultStudentCount);

    List<TeachersDescription> findAllByTeachersPermanentPreferencesInAndDefaultPrice(Collection<Set<TeachersPermanentPreference>> teachersPermanentPreferences, BigDecimal defaultPrice);
}
