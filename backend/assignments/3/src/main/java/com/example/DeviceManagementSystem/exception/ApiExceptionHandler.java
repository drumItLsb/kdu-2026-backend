package com.example.DeviceManagementSystem.exception;

import com.example.DeviceManagementSystem.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleAlreadyExists(ResourceAlreadyExistsException ex, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorResponseDTO(Instant.now(), 409, "Conflict", ex.getMessage(), req.getRequestURI())
        );
    }

    @ExceptionHandler({UserNotFoundException.class,NotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handleNotFoundExceptions(Exception ex, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponseDTO(Instant.now(), 404, "NotFound", ex.getMessage(), req.getRequestURI())
        );
    }

    @ExceptionHandler(UnAuthorizedAccessException.class)
    public ResponseEntity<ErrorResponseDTO> handleUnAuthorizedAccessExceptions(UnAuthorizedAccessException ex, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ErrorResponseDTO(Instant.now(), 401, "Unauthorized", "User is not authorized to access this resource", req.getRequestURI())
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadCreds(BadCredentialsException ex, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ErrorResponseDTO(Instant.now(), 401, "Unauthorized", "Invalid username or password", req.getRequestURI())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .orElse("Validation error");

        return ResponseEntity.badRequest().body(
                new ErrorResponseDTO(Instant.now(), 400, "Bad Request", msg, req.getRequestURI())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneric(Exception ex, HttpServletRequest req) {
        if(ex.getMessage().contains("Access Denied")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseDTO(Instant.now(), 403, "Unauthorized operation", ex.getMessage(), req.getRequestURI()));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorResponseDTO(Instant.now(), 500, "Internal Server Error", ex.getMessage(), req.getRequestURI())
        );
    }
}
