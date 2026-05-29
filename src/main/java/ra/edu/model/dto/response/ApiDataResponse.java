package ra.edu.model.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiDataResponse<T> {
    private Boolean success;
    private String message;
    private T data;
    private Object errors;
    private HttpStatus status;
}