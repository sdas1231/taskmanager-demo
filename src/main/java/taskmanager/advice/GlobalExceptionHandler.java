package taskmanager.advice;

import java.text.ParseException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.context.request.WebRequest;

import taskmanager.dto.ErrorResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<?> handleErrors(ParseException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid date format"));
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<?> handleErrors(NotFound ex, WebRequest request) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleErrors(Exception e) {
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server Error"));
    }
}
