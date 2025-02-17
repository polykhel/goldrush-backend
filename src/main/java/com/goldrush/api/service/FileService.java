package com.goldrush.api.service;

import com.goldrush.api.dto.response.FileResponse;
import com.goldrush.api.exception.FileException;
import io.minio.*;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class FileService {

  private final MinioClient minioClient;

  @Value("${minio.bucketName}")
  private String bucketName;

  public FileService(MinioClient minioClient) {
    this.minioClient = minioClient;
  }

  public void uploadFile(MultipartFile file) {
    try {
      if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
        minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
      }

      String fileName = file.getOriginalFilename();
      InputStream inputStream = file.getInputStream();
      String contentType = file.getContentType();

      minioClient.putObject(
          PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(
                  inputStream, file.getSize(), -1) // -1 for unknown size
              .contentType(contentType)
              .build());

      log.info("File uploaded successfully: {}", fileName);

    } catch (Exception e) {
      throw new FileException("Error uploading file: " + e.getMessage());
    }
  }

  public FileResponse getFile(String fileName) {
    try {
      // Fetch metadata (stat object)
      StatObjectResponse objectStat =
          minioClient.statObject(
              io.minio.StatObjectArgs.builder().bucket(bucketName).object(fileName).build());

      // Extract Content-Type and Content-Length
      String contentType = objectStat.contentType();
      long contentLength = objectStat.size();

      // Retrieve the actual file object
      byte[] fileData =
          minioClient
              .getObject(
                  io.minio.GetObjectArgs.builder().bucket(bucketName).object(fileName).build())
              .readAllBytes();

      // Create response object
      return new FileResponse(fileName, fileData, contentType, contentLength);

    } catch (Exception e) {
      throw new FileException("Error getting file: " + e.getMessage());
    }
  }
}
