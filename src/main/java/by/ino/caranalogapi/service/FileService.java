package by.ino.caranalogapi.service;

import java.nio.file.Path;


public interface FileService {
    Path getPathByPhotoId(Long id);
    String getMediaTypeFromPath(String path);
}
