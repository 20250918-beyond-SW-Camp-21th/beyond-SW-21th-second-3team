package com.team3.what_if_workout.feed.service;

import com.team3.what_if_workout.feed.domain.Image2;
import com.team3.what_if_workout.feed.repository.ImageRepository;
import io.minio.*;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MinioFileService {

    private final MinioClient minioClient;
    private final ImageRepository imageRepository;

    @Value("${minio.url}")
    private String url;

    @Value("${minio.bucket}")
    private String BUCKET_NAME;

    @Value("${minio.dir.image}")
    private String IMAGE_DIR;

    @Value("${minio.path}")
    private String PATH;

    public String saveImageFile(MultipartFile multipartFile)
            throws IOException, ServerException, InsufficientDataException,
            ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {

        boolean isExist = minioClient.bucketExists(
                BucketExistsArgs.builder()
                        .bucket(BUCKET_NAME)
                        .build());

        if (!isExist) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(BUCKET_NAME)
                    .build());
        }


        String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
        String objectName = IMAGE_DIR + "/" + fileName;

        minioClient.putObject(PutObjectArgs.builder()
                .bucket(BUCKET_NAME)
                .object(objectName)
                .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                .contentType(multipartFile.getContentType())
                .build());

        String fullUrl = url + "/" + BUCKET_NAME + "/" + objectName;

        imageRepository.save(
                Image2.builder()
                        .originalFileName(multipartFile.getOriginalFilename())
                        .fileName(fileName)
                        .fileContent(fullUrl)
                        .build()
        );
        return fullUrl;
    }


    public void deleteImage(String fileName)
            throws ServerException, InsufficientDataException, ErrorResponseException,
            IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {

        String objectName = IMAGE_DIR + "/" + fileName;
        log.info("삭제 요청 objectName = {}", objectName);

        if (isHaveImage(objectName)) {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(BUCKET_NAME)
                            .object(objectName)
                            .build());
            log.info("삭제 성공: {}", objectName);
        } else {
            log.warn("파일 없음: {}", objectName);
        }
    }

    public boolean isHaveImage(String objectName) {
        try {
            minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(BUCKET_NAME)
                            .object(objectName)
                            .build()
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteImageByUrl(String imageUrl) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        deleteImage(fileName);
    }
}