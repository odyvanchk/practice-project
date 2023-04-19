package com.practice.shop.service.config;

import com.practice.shop.model.exception.StorageException;
import com.practice.shop.service.property.MinioProperty;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {

    private final MinioProperty minioProperty;

    @Bean
    public MinioClient minioClient() {
        try {
            return MinioClient.builder()
                    .endpoint(this.minioProperty.getUrl())
                    .credentials(this.minioProperty.getAccessKey(), this.minioProperty.getSecretKey())
                    .build();
        } catch (Exception e) {
            throw new StorageException("Unable to connect to minio!");
        }
    }

}
