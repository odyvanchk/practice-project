package com.practice.shop.service.impl;

import com.practice.shop.model.exception.EntityAlreadyExistsException;
import com.practice.shop.model.exception.EntityNotFoundException;
import com.practice.shop.model.lesson.Lesson;
import com.practice.shop.model.lesson.LessonResultList;
import com.practice.shop.model.lesson.LessonsStatus;
import com.practice.shop.repository.LessonRepository;
import com.practice.shop.repository.ScheduleRepository;
import com.practice.shop.service.EmailService;
import com.practice.shop.service.LessonService;
import com.practice.shop.web.controller.security.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final ScheduleRepository scheduleRepository;
    private final EmailService emailService;
    private static final int PAGE_SIZE = 2;

    @Override
    @Transactional
    public void cancelByTeacher(Long lessonId) {
        var lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() ->
                        new EntityAlreadyExistsException("lesson is not found")
                );
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if (!lessonRepository.existsByIdTeacherAndId(userDetails.getId(), lessonId)) {
            throw new AccessDeniedException("access denied");
        }
//        emailService
        lesson.setStatus(LessonsStatus.CANCELLED_BY_TEACHER);
        lessonRepository.save(lesson);
    }

    @Override
    @Transactional
    public void cancelByStudent(Long lessonId) {
        var lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() ->
                        new EntityAlreadyExistsException("lesson is not found")
                );
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if (!lessonRepository.existsByIdAndIdStudent(lessonId, userDetails.getId())) {
            throw new AccessDeniedException("access denied");
        }
        lesson.setStatus(LessonsStatus.CANCELLED_BY_STUDENT);
        scheduleRepository.updateIsAvailableById(lesson.getSchedule().getId());
        lessonRepository.save(lesson);
    }

    @Override
    public LessonResultList findFutureByTeacherId(Long id, int page) {
        var res = new LessonResultList();
        res.setLessons(
                lessonRepository.findFutureByIdTeacher(id,
                        PageRequest.of(
                                page,
                                PAGE_SIZE,
                                Sort.by("dateTime")
                        )
                )
        );
        res.setPageSize(PAGE_SIZE);
        res.setCurrentPage((long) page);
        res.setTotalCount(lessonRepository.countFutureByIdTeacher(id));
        return res;
    }

    @Override
    public LessonResultList findPastByTeacherId(Long id, int page) {
        var res = new LessonResultList();
        res.setLessons(
                lessonRepository.findPastByIdTeacher(id,
                        PageRequest.of(
                        page,
                        PAGE_SIZE,
                        Sort.by("dateTime")
                                .descending()
                        )
                )
        );
        res.setPageSize(PAGE_SIZE);
        res.setCurrentPage((long) page);
        res.setTotalCount(lessonRepository.countPastByIdTeacher(id));
        return res;
    }

    @Override
    public LessonResultList findPastByStudentId(Long studentId, int page) {
        var res = new LessonResultList();
        res.setLessons(
                lessonRepository.findPastByIdStudent(studentId,
                        PageRequest.of(
                                page,
                                PAGE_SIZE,
                                Sort.by("dateTime")
                                        .descending()
                        )
                )
        );
        res.setPageSize(PAGE_SIZE);
        res.setCurrentPage((long) page);
        res.setTotalCount(lessonRepository.countPastByIdStudent(studentId));
        return res;
    }

    @Override
    public LessonResultList findFutureByStudentId(Long studentId, int page) {
        var res = new LessonResultList();
        res.setLessons(
                lessonRepository.findFutureByIdTeacher(studentId,
                    PageRequest.of(
                        page,
                        PAGE_SIZE,
                        Sort.by("dateTime")
                )
        ));
        res.setPageSize(PAGE_SIZE);
        res.setCurrentPage((long) page);
        res.setTotalCount(lessonRepository.countFutureByIdStudent(studentId));
        return res;
    }

    @Override
    public Lesson findById(Long lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException("Lesson does not exist"));
    }

    @Override
    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

}
