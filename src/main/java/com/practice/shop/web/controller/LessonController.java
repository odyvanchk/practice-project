package com.practice.shop.web.controller;


import com.practice.shop.service.LessonService;
import com.practice.shop.web.mappers.LessonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;
    private final LessonMapper lessonMapper;

//    @PostMapping("/book")
//    @PreAuthorize("hasRole('STUDENT')")
//    @ResponseStatus(HttpStatus.CREATED)
//    public LessonDto book(@RequestBody BookLessonDto bookLessonDto) {
//        Lesson lesson = lessonService.book(bookLessonDto.getLessonIdFromSchedule(), bookLessonDto.getUserId());
//        return lessonMapper.toDto(lesson);
//    }

    @GetMapping("/{lessonId}/cancelByTeacher")
    @PreAuthorize("hasRole('TEACHER')")
    //todo add expression that student can cancell only his lesson
    public void cancel(@PathVariable Long lessonId) {
        lessonService.cancelByTeacher(lessonId);
    }


}
