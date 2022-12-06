package za.flatrock.assessment.demo.services.users.processor;

import za.flatrock.assessment.demo.models.dtos.UserAPIResponse;
import za.flatrock.assessment.demo.models.dtos.role.RoleUpdateDTO;
import za.flatrock.assessment.demo.models.dtos.user.CreateUserRequestDTO;
import za.flatrock.assessment.demo.models.dtos.user.UserSearchDTO;

public interface UserProcessorService {
    UserAPIResponse createUser(CreateUserRequestDTO request);

    UserAPIResponse deleteUserById(String id);

    UserAPIResponse updateRole(RoleUpdateDTO roleUpdate);

    UserAPIResponse findUser(UserSearchDTO userSearch);
}
