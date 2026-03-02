package by.ino.caranalogapi.controller;

import by.ino.caranalogapi.model.Photos;
import by.ino.caranalogapi.model.RequestDto;
import by.ino.caranalogapi.repository.PhotosRepository;
import by.ino.caranalogapi.security.ApiKeyProtected;
import by.ino.caranalogapi.service.FileService;
import by.ino.caranalogapi.service.SearchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RestController
@CrossOrigin
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class RequestController {
    final SearchService searchService;
    final FileService fileService;

    @PostMapping("/search")
    @ApiKeyProtected
    public ResponseEntity<?> findAll(@RequestBody @Valid RequestDto requestDto) {
        return ResponseEntity.ok(searchService.search(requestDto));
    }

    @GetMapping("/photos/{id}")
    public ResponseEntity<Resource> getPhotoById(@PathVariable Long id) {
        Path filePath = fileService.getPathByPhotoId(id);
        Resource resource = new FileSystemResource(filePath);
        String mediaType = fileService.getMediaTypeFromPath(filePath.toString());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mediaType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filePath.getFileName() + "\"")
                .body(resource);
    }
}
