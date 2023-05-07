package com.practice.shop.repository;

import com.practice.shop.model.lesson.Lesson;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("select count(l) from Lesson l where l.idStudent = ?1 and l.dateTime < now()")
    long countPastByIdStudent(Long idStudent);

    @Query("select count(l) from Lesson l where l.idStudent = ?1 and l.dateTime > now()")
    long countFutureByIdStudent(Long idStudent);

    @Query("select count(l) from Lesson l where l.idTeacher = ?1 and l.dateTime < now()")
    long countPastByIdTeacher(Long idTeacher);

    @Query("select count(l) from Lesson l where l.idTeacher = ?1 and l.dateTime > now()")
    long countFutureByIdTeacher(Long idTeacher);

    boolean existsByIdAndIdStudent(Long id, Long idStudent);

    boolean existsByIdTeacherAndId(Long idTeacher, Long id);

    @Query("select l from Lesson l where l.idTeacher = ?1 and l.dateTime > now()")
    List<Lesson> findFutureByIdTeacher(Long idTeacher, Pageable pageable);

    @Query("select l from Lesson l where l.idTeacher = ?1 and l.dateTime < now()")
    List<Lesson> findPastByIdTeacher(Long id, PageRequest dateTime);

    @Query("select l from Lesson l where l.idStudent = ?1 and l.dateTime < now()")
    List<Lesson> findPastByIdStudent(Long studentId, PageRequest dateTime);

    @Query("select l from Lesson l where l.idStudent = ?1 and l.dateTime > now()")
    List<Lesson> findFutureByIdStudent(Long idStudent, Pageable pageable);

}
