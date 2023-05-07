package com.practice.shop.service;

import com.practice.shop.model.TeachersDescription;
import com.practice.shop.model.lesson.LessonResultList;
import org.springframework.web.multipart.MultipartFile;

public interface TeacherService {

    TeachersDescription save(MultipartFile file, TeachersDescription teacherInfo);

    TeachersDescription get(Long id);

    LessonResultList findFutureLessons(Long teacherId, int page);

    LessonResultList findPastLessons(Long teacherId, int page);

}
