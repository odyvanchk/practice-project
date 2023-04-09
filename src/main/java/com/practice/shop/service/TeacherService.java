package com.practice.shop.service;

import com.practice.shop.model.user.TeachersDescription;
import org.springframework.web.multipart.MultipartFile;

public interface TeacherService {

    TeachersDescription save(MultipartFile file, TeachersDescription teacherInfo);

    TeachersDescription get(Long id);

    TeachersDescription update(MultipartFile image, TeachersDescription teacherInfo);
}
