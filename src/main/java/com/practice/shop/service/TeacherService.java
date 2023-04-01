package com.practice.shop.service;

import com.practice.shop.model.user.TeachersDescription;
import org.springframework.web.multipart.MultipartFile;

public interface TeacherService {

    TeachersDescription saveInfo(MultipartFile file, TeachersDescription teacherInfo);

}
