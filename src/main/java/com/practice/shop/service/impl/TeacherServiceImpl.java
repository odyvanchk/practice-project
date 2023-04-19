package com.practice.shop.service.impl;

import com.practice.shop.model.TeachersDescription;
import com.practice.shop.model.exception.UserNotFoundException;
import com.practice.shop.repository.TchDescnRepository;
import com.practice.shop.service.StorageService;
import com.practice.shop.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final StorageService storageService;
    private final TchDescnRepository tchDescnRepository;

    @Override
    @Transactional
    public TeachersDescription save(MultipartFile file, TeachersDescription teacherInfo) {
        if (!file.isEmpty()) {
            String path = this.storageService.uploadPhoto(file, teacherInfo);
            teacherInfo.setPhotoRef(path);
        }
        return tchDescnRepository.save(teacherInfo);
    }

    @Override
    public TeachersDescription get(Long id) {
        return tchDescnRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public TeachersDescription update(MultipartFile file, TeachersDescription teacherInfo) {
        if (!file.isEmpty()) {
            String path = this.storageService.uploadPhoto(file, teacherInfo);
            teacherInfo.setPhotoRef(path);
        }
        return tchDescnRepository.save(teacherInfo);
    }

}
