package com.goldrush.api.dto.response;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public record FileResponse(String fileName, byte[] data, String contentType, long contentLength) {

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FileResponse that = (FileResponse) o;
    return Objects.equals(fileName, that.fileName)
        && contentLength == that.contentLength
        && Objects.equals(contentType, that.contentType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileName, contentType, contentLength);
  }

  @NotNull
  @Override
  public String toString() {
    return "FileResponse{"
        + "fileName='"
        + fileName
        + "contentType='"
        + contentType
        + '\''
        + ", contentLength="
        + contentLength
        + '}';
  }
}
