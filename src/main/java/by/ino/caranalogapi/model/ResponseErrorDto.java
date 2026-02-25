package by.ino.caranalogapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ResponseErrorDto {
    String error;
    String message;
    List<FieldErrorDto> details = new ArrayList<>();
    LocalDateTime timestamp;

    @Data
    @AllArgsConstructor
    public static class FieldErrorDto {
        String field;
        String message;
    }

    public ResponseErrorDto(String error, String message) {
        this.error = error;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}

