package za.flatrock.assessment.demo.models.responses.api;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import za.flatrock.assessment.demo.models.responses.UserResponse;

import java.util.List;

@Getter
public class ApiSearchCriteriaResponse extends ApiResponse{
    private List<UserResponse> data;

    public ApiSearchCriteriaResponse(List<UserResponse> searchResults) {
        this.setStatus(HttpStatus.OK);
        this.data = searchResults;
    }
}
