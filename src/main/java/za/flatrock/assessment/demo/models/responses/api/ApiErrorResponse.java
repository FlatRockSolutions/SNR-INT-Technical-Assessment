package za.flatrock.assessment.demo.models.responses.api;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
public class ApiErrorResponse extends ApiResponse{
    private List<String> errors;

    public ApiErrorResponse(List<String> errors, HttpStatus httpStatus) {
        this.setStatus(httpStatus);
        this.errors = errors;
    }
    public ApiErrorResponse(String error, HttpStatus httpStatus) {
        this.setStatus(httpStatus);
        this.errors = Arrays.asList(error);
    }
}
