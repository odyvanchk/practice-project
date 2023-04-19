package com.practice.shop.service.impl;


import com.practice.shop.model.TeachersDescription;
import com.practice.shop.model.exception.StorageException;
import com.practice.shop.service.StorageService;
import com.practice.shop.service.property.MinioProperty;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.compress.utils.FileNameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final MinioClient minioClient;
    private final MinioProperty minioProperty;

    @Override
    public String uploadPhoto(MultipartFile file, TeachersDescription teacherInfo) {
        try {
            this.createBucket();
            String path = "users/" + teacherInfo.getId() + "/200/" + file.getOriginalFilename();
            this.savePhoto(path, this.generateThumbnail(file, 200));
            path = "users/" + teacherInfo.getId() + "/original/" + file.getOriginalFilename();
            this.savePhoto(path, new ByteArrayInputStream(file.getBytes()));
            return path;
        } catch (Exception ex) {
            throw new StorageException("Unable to upload photo, try again!");
        }
    }

    @SneakyThrows
    private void createBucket() {
        boolean found = this.minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(this.minioProperty.getBucket())
                .build());
        if (!found) {
            this.minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(this.minioProperty.getBucket())
                    .build());
        }
    }

    @SneakyThrows
    private void savePhoto(String path, InputStream inputStream) {
        this.minioClient.putObject(PutObjectArgs.builder()
                .bucket(this.minioProperty.getBucket())
                .object(path)
                .stream(inputStream, inputStream.available(), -1)
                .build());
    }

    @SneakyThrows
    private InputStream generateThumbnail(MultipartFile photo, Integer height) {
        String extension = FileNameUtils.getExtension(photo.getOriginalFilename());
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(photo.getBytes()));
        Thumbnails.Builder<BufferedImage> builder = Thumbnails.of(originalImage)
                .height(height)
                .outputFormat(extension);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        builder.toOutputStream(outputStream);
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

}
