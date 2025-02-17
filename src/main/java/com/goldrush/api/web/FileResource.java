package com.goldrush.api.web;

import com.goldrush.api.dto.response.FileResponse;
import com.goldrush.api.service.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/file")
public class FileResource {

  private final FileService fileService;

  public FileResource(FileService fileService) {
    this.fileService = fileService;
  }

  @GetMapping("/{fileName}")
  public ResponseEntity<ByteArrayResource> getFile(@PathVariable String fileName) {
    try {
      FileResponse fileData = fileService.getFile(fileName);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.valueOf(fileData.contentType()));
      headers.setContentLength(fileData.contentLength());

      ByteArrayResource resource = new ByteArrayResource(fileData.data());

      return ResponseEntity.ok().headers(headers).body(resource);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
