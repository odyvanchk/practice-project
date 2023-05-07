package com.practice.shop.web.controller;

import com.practice.shop.model.lesson.Lesson;
import com.practice.shop.model.lesson.LessonResultList;
import com.practice.shop.service.StudentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ermakovich Kseniya
 */
@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping(value = "/{id}/blacklist")
    @PreAuthorize("hasRole('STUDENT')")
    public void banTeacher(@RequestBody Long teacherId) {
        studentService.ban(teacherId);
    }


    @GetMapping("/{studentId}/lessons/future")
    @PreAuthorize("hasRole('STUDENT')")
    //todo add expression that student can cancell only his lesson
    public LessonResultList getFutureLessons (@PathVariable Long studentId,
                                          @RequestParam(defaultValue = "0", required = false) int page) {
        return studentService.findFutureLessons(studentId, page);
    }

    @GetMapping("/{studentId}/lessons/past")
    @PreAuthorize("hasRole('STUDENT')")
    //todo add expression that student can cancell only his lesson
    public LessonResultList getPastLessons (@PathVariable Long studentId,
                                            @RequestParam(defaultValue = "0", required = false) int page) {
        return studentService.findPastLessons(studentId, page);
    }

    @PostMapping("/{studentId}/lessons/{lessonId}/note")
    @PreAuthorize("hasRole('STUDENT')")
    //todo add expression that student can cancell only his lesson
    public Lesson addNoteToLesson (@PathVariable Long studentId, @PathVariable Long lessonId,
                                         @RequestBody String note) {
        return studentService.addNote(lessonId, note);
    }
}
