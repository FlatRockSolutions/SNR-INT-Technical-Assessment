package za.flatrock.assessment.demo.models.responses.api;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse {
    private HttpStatus status;
}
