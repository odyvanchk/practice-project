package com.practice.shop.web.controller;

import com.practice.shop.model.TchDescriptionResultList;
import com.practice.shop.model.TeachersDescription;
import com.practice.shop.model.TeachersDescriptionCriteria;
import com.practice.shop.model.lesson.Lesson;
import com.practice.shop.service.SearchLessonService;
import com.practice.shop.service.TeacherService;
import com.practice.shop.web.dto.TchDescriptionResultListDto;
import com.practice.shop.web.dto.TeachersDescriptionCriteriaDto;
import com.practice.shop.web.dto.TeachersDescriptionDto;
import com.practice.shop.web.mappers.TchDescriptionResultListMapper;
import com.practice.shop.web.mappers.TeachersCriteriaMapper;
import com.practice.shop.web.mappers.TeachersDescriptionMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final SearchLessonService searchLessonService;
    private final TeacherService teacherService;
    private final TeachersCriteriaMapper criteriaMapper;
    private final TchDescriptionResultListMapper descriptionListMapper;
    private final TeachersDescriptionMapper descriptionMapper;

    @PreAuthorize("hasRole('STUDENT') or hasRole('TEACHER')")
    @GetMapping("/search")
    public TchDescriptionResultListDto retrieveTeacherList(TeachersDescriptionCriteriaDto criteriaDto, @RequestParam(defaultValue = "0", required = false) Long page) {
        TeachersDescriptionCriteria criteria = criteriaMapper.dtoToEntity(criteriaDto);
        TchDescriptionResultList teachersDescriptions = searchLessonService.searchByParams(criteria, page);
        return descriptionListMapper.entityToDto(teachersDescriptions);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public TeachersDescriptionDto fillInfo(@ModelAttribute TeachersDescriptionDto teachersDescriptionDto) {
        var teacherInfo = descriptionMapper.dtoToEntity(teachersDescriptionDto);
        teacherService.save(teachersDescriptionDto.getImage(), teacherInfo);
        return descriptionMapper.entityToDto(teacherInfo);
    }

    @GetMapping(value = "/{id}")
    public TeachersDescriptionDto getInfo(@PathVariable Long id) {
        TeachersDescription descriptionDto = teacherService.get(id);
        return descriptionMapper.entityToDto(descriptionDto);
    }

    @GetMapping("/{teacherId}/lessons/future")
    @PreAuthorize("hasRole('TEACHER')")
    //todo add expression that student can cancell only his lesson
    public List<Lesson> getFutureLessons (@PathVariable Long teacherId, @RequestParam(defaultValue = "0", required = false) int page) {
       return teacherService.findFutureLessons(teacherId, page);
    }

    @GetMapping("/{teacherId}/lessons/past")
    @PreAuthorize("hasRole('TEACHER')")
    //todo add expression that student can cancell only his lesson
    public List<Lesson> getPastLessons (@PathVariable Long teacherId, @RequestParam(defaultValue = "0", required = false) int page) {
        return teacherService.findPastLessons(teacherId, page);
    }

}
