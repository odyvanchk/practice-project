package com.practice.shop.service.impl;

import com.practice.shop.model.BlackList;
import com.practice.shop.model.BlackListPK;
import com.practice.shop.model.lesson.Lesson;
import com.practice.shop.model.lesson.LessonResultList;
import com.practice.shop.repository.BlackListRepository;
import com.practice.shop.service.EmailService;
import com.practice.shop.service.LessonService;
import com.practice.shop.service.StudentService;
import com.practice.shop.service.UserService;
import com.practice.shop.web.controller.security.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final BlackListRepository blackListRepository;
    private final LessonService lessonService;
    private final EmailService emailService;
    private final UserService userService;

    @Override
    public void ban(Long teacherId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        blackListRepository.save(new BlackList(new BlackListPK(userDetails.getId(), teacherId)));
    }

    @Override
    public LessonResultList findFutureLessons(Long studentId, int page) {
        return lessonService.findFutureByStudentId(studentId, page);
    }

    @Override
    public LessonResultList findPastLessons(Long studentId, int page) {
        return lessonService.findPastByStudentId(studentId, page);
    }

    @Override
    public Lesson addNote(Long lessonId, String note) {
        var lesson = lessonService.findById(lessonId);
        lesson.setNote(note);
        var teacher = userService.findById(lesson.getTeacher().getId());
        emailService.studentAddNote(lesson, teacher.getEmail(), "Note to lesson");
        return lessonService.save(lesson);
    }
}
