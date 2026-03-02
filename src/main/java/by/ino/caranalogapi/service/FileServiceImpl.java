package by.ino.caranalogapi.service;

import by.ino.caranalogapi.aspect.Logging;
import by.ino.caranalogapi.exception.NotFoundException;
import by.ino.caranalogapi.model.Photos;
import by.ino.caranalogapi.repository.PhotosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    final PhotosRepository photosRepository;

    @Value("${photos.base-path:./}")
    String photosBasePath;

    static final String PHOTOS_NOT_EXIST = "Photos with id %s does not exist";
    static final String FILE_NOT_EXIST = "File for photo with id %s does not exist";
    static final Map<String, String> EXTENSION_TO_MEDIA_TYPE = Map.of(
            ".jpg", "image/jpeg",
            ".jpeg", "image/jpeg",
            ".png", "image/png",
            ".gif", "image/gif",
            ".webp", "image/webp"
    );

    @Override
    public Path getPathByPhotoId(Long id) {
        Photos photo = findById(id);

        Path filePath = Path.of(photo.getPath()).isAbsolute()
                ? Path.of(photo.getPath())
                : Path.of(photosBasePath, photo.getPath()).normalize();
        if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            throw new NotFoundException(String.format(FILE_NOT_EXIST, id));
        }

        return filePath;
    }

    private Photos findById(long id) {
        return photosRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(PHOTOS_NOT_EXIST, id)));
    }

    @Override
    public String getMediaTypeFromPath(String path) {
        if (path == null) return "application/octet-stream";
        int dot = path.lastIndexOf('.');
        if (dot < 0) return "application/octet-stream";
        return EXTENSION_TO_MEDIA_TYPE.getOrDefault(path.substring(dot).toLowerCase(), "application/octet-stream");
    }
}
