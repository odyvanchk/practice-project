package com.practice.shop.service;

import com.practice.shop.model.TeachersDescription;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String uploadPhoto(MultipartFile file, TeachersDescription teacherInfo);

}
