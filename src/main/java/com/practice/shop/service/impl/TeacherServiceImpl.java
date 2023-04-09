package com.practice.shop.service.impl;

import com.practice.shop.model.exception.UserNotFoundException;
import com.practice.shop.model.user.TeachersDescription;
import com.practice.shop.repository.TeacherServiceRepository;
import com.practice.shop.service.TeacherService;
import com.practice.shop.service.utils.FileUploadUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherServiceRepository teacherServiceRepository;

    @Override
    @Transactional
    public TeachersDescription save(MultipartFile file, TeachersDescription teacherInfo) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "user-photos/" + teacherInfo.getId();

        try {
            FileUploadUtils.saveFile(uploadDir, fileName, file);
        } catch (IOException e) {
            throw new RuntimeException("sorry, try again later");
        }
        teacherInfo.setPhotoRef(uploadDir + "/" + fileName);
        return teacherServiceRepository.save(teacherInfo);
    }

    @Override
    public TeachersDescription get(Long id) {
        return teacherServiceRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public TeachersDescription update(MultipartFile file, TeachersDescription teacherInfo) {
        if (!file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String uploadDir = "user-photos/" + teacherInfo.getId();

            try {
                FileUploadUtils.saveFile(uploadDir, fileName, file);
            } catch (IOException e) {
                throw new RuntimeException("sorry, try again later");
            }
            teacherInfo.setPhotoRef(uploadDir + "/" + fileName);
        }
        return teacherServiceRepository.save(teacherInfo);
    }

}
