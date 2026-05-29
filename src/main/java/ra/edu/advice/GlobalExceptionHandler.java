package ra.edu.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.edu.exception.NotFoundException;
import ra.edu.model.dto.response.ApiDataResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiDataResponse<Map<String,String>>> handleValidate(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();

        for(FieldError error : ex.getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage()
            );
        }

        log.error("Validation Error: {}", errors);

        return new ResponseEntity<>(
                new ApiDataResponse<>(
                        false,
                        "Dữ liệu không hợp lệ",
                        null,
                        errors,
                        HttpStatus.BAD_REQUEST
                ),

                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiDataResponse<String>> handleNotFound(NotFoundException ex){
        log.error("Not Found Error: {}", ex.getMessage());

        return new ResponseEntity<>(
                new ApiDataResponse<>(
                        false,
                        "Không tìm thấy dữ liệu",
                        null,
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiDataResponse<String>> handleException(Exception ex){
        log.error("System Error: ", ex);

        return new ResponseEntity<>(
                new ApiDataResponse<>(
                        false,
                        "Có lỗi xảy ra",
                        null,
                        ex.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR
                ),

                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}