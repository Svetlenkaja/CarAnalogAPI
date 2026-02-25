package by.ino.caranalogapi.controller;

import by.ino.caranalogapi.exception.ServiceException;
import by.ino.caranalogapi.model.ResponseErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ResponseErrorDto> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ResponseErrorDto.FieldErrorDto> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::toDetail)
                .toList();

        String message = details.isEmpty()
                ? "Validation failed"
                : details.getFirst().getMessage();
        // логируем BAD_REQUEST с валидацией
        String params = ex.getBindingResult().getTarget() == null
                ? ""
                : ex.getBindingResult().getTarget().toString();

        ResponseErrorDto body = new ResponseErrorDto(
                "VALIDATION_ERROR",
                message,
                details,
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(body);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ResponseEntity<ResponseErrorDto> handleMissingRequestHeaders(MissingRequestHeaderException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseErrorDto("BAD_REQUEST", ex.getMessage()));

    }
    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<ResponseErrorDto> handleServiceException(ServiceException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseErrorDto("INTERNAL_SERVER_ERROR", ex.getMessage()));
    }

    private ResponseErrorDto.FieldErrorDto toDetail(FieldError fieldError) {
        return new ResponseErrorDto.FieldErrorDto(
                fieldError.getField(),
                fieldError.getDefaultMessage()
        );
    }
}

