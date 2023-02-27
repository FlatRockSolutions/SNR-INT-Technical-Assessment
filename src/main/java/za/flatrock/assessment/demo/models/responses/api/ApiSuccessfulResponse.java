package za.flatrock.assessment.demo.models.responses.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Data
public class ApiSuccessfulResponse extends ApiResponse{
    private Object data;

    public ApiSuccessfulResponse(Object data) {
        this.setStatus(HttpStatus.OK);
        this.data = data;
    }
}
