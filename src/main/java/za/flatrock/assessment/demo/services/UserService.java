package za.flatrock.assessment.demo.services;

import org.springframework.validation.Errors;
import za.flatrock.assessment.demo.models.SearchCriteria;
import za.flatrock.assessment.demo.models.requests.CreateUserRequest;
import za.flatrock.assessment.demo.models.responses.api.ApiResponse;

public interface UserService {
    ApiResponse search(SearchCriteria searchCriteria);

    ApiResponse create(CreateUserRequest createUserRequest, Errors validationError);

    ApiResponse deleteUser(Long id);

}
