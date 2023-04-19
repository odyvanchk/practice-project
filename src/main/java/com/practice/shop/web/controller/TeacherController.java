package com.practice.shop.web.controller;

import com.practice.shop.model.TeachersDescriptionCriteria;
import com.practice.shop.model.TeachersDescription;
import com.practice.shop.service.SearchLessonService;
import com.practice.shop.service.TeacherService;
import com.practice.shop.web.dto.TeachersDescriptionCriteriaDto;
import com.practice.shop.web.dto.TeachersDescriptionDto;
import com.practice.shop.web.mappers.TeachersCriteriaMapper;
import com.practice.shop.web.mappers.TeachersDescriptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final SearchLessonService searchLessonService;
    private final TeacherService teacherService;
    private final TeachersCriteriaMapper criteriaMapper;
    private final TeachersDescriptionMapper descriptionMapper;

    @GetMapping("/search")
    public List<TeachersDescriptionDto> retrieveTeacherList(TeachersDescriptionCriteriaDto criteriaDto, @RequestParam(defaultValue = "0", required = false) Long page) {
        TeachersDescriptionCriteria criteria = criteriaMapper.dtoToEntity(criteriaDto);
        List<TeachersDescription> teachersDescriptions = searchLessonService.searchByParams(criteria, page);
        return descriptionMapper.entityToDto(teachersDescriptions);
    }

    @PostMapping(value = "/{id}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public TeachersDescriptionDto fillInfo (@PathVariable Long id, @ModelAttribute TeachersDescriptionDto teachersDescriptionDto) {
        var teacherInfo = descriptionMapper.dtoToEntity(teachersDescriptionDto);
        teacherService.save(teachersDescriptionDto.getImage(), teacherInfo);
        return descriptionMapper.entityToDto(teacherInfo);
    }

    @PutMapping(value = "/{id}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public TeachersDescriptionDto updateInfo (@PathVariable Long id, @ModelAttribute TeachersDescriptionDto teachersDescriptionDto) {
        var teacherInfo = descriptionMapper.dtoToEntity(teachersDescriptionDto);
        teacherService.update(teachersDescriptionDto.getImage(), teacherInfo);
        return descriptionMapper.entityToDto(teacherInfo);
    }

    @GetMapping(value = "/{id}")
    public TeachersDescriptionDto getInfo (@PathVariable Long id) {
        TeachersDescription descriptionDto = teacherService.get(id);
        return descriptionMapper.entityToDto(descriptionDto);
    }

}
