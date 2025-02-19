package com.goldrush.api.service;

import com.goldrush.api.dto.response.FileResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FileService {

  @Value("${minio.bucketName}")
  private String bucketName;

  public FileResponse getFile(String fileName) {
    return null;
  }
}
