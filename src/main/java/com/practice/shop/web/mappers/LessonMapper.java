package com.practice.shop.web.mappers;

import com.practice.shop.model.lesson.Lesson;
import com.practice.shop.web.dto.LessonDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    Lesson toEntity(LessonDto lessonDto);

    LessonDto toDto(Lesson lesson);

    List<LessonDto> toDto(List<Lesson> lessons);

}
