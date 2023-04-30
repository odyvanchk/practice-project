package com.practice.shop.service;

import com.practice.shop.model.TeachersDescription;
import com.practice.shop.model.lesson.Lesson;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface TeacherService {

    TeachersDescription save(MultipartFile file, TeachersDescription teacherInfo);

    TeachersDescription get(Long id);

    List<Lesson> findFutureLessons(Long teacherId, int page);

    List<Lesson> findPastLessons(Long teacherId, int page);

}
