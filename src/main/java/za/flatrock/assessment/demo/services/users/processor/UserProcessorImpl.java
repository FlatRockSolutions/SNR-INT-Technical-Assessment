package za.flatrock.assessment.demo.services.users.processor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import za.flatrock.assessment.demo.business.logic.UserManagementService;
import za.flatrock.assessment.demo.models.dtos.UserAPIResponse;
import za.flatrock.assessment.demo.models.dtos.role.RoleUpdateDTO;
import za.flatrock.assessment.demo.models.dtos.user.CreateUserRequestDTO;
import za.flatrock.assessment.demo.models.dtos.user.DeleteUserResponseDTO;
import za.flatrock.assessment.demo.models.dtos.user.UserResponseDTO;
import za.flatrock.assessment.demo.models.dtos.user.UserSearchDTO;

@Service
public class UserProcessorImpl implements UserProcessorService {
    private final UserManagementService userManagementService;

    public UserProcessorImpl(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @Override
    public UserAPIResponse createUser(CreateUserRequestDTO request) {
        UserAPIResponse responseDTO = new UserAPIResponse();
        try {
            UserResponseDTO response = userManagementService.createUser(request);
            responseDTO.setResponseCode(HttpStatus.CREATED.value());
            responseDTO.setHttpStatus(HttpStatus.CREATED);
            responseDTO.setMessage("Account created successfully");
            responseDTO.setModified(true);
            responseDTO.setData(response);
        } catch (Exception e) {
            responseDTO.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setModified(false);

        } finally {
            return responseDTO;
        }
    }

    @Override
    public UserAPIResponse deleteUserById(String id) {
        UserAPIResponse responseDTO = new UserAPIResponse();
        try {
            DeleteUserResponseDTO response = userManagementService.deleteUserById(id);
            responseDTO.setResponseCode(HttpStatus.OK.value());
            responseDTO.setHttpStatus(HttpStatus.OK);
            responseDTO.setMessage(response.getMessage());
            responseDTO.setModified(true);
        } catch (Exception e) {
            responseDTO.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setModified(false);

        } finally {
            return responseDTO;
        }
    }

    @Override
    public UserAPIResponse updateRole(RoleUpdateDTO roleUpdate) {
        UserAPIResponse responseDTO = new UserAPIResponse();
        try {
            UserResponseDTO updatedUserResponseDTO = userManagementService.updateRole(roleUpdate);
            responseDTO.setData(updatedUserResponseDTO);
            responseDTO.setResponseCode(HttpStatus.OK.value());
            responseDTO.setHttpStatus(HttpStatus.OK);
            responseDTO.setMessage("Role updated successfully");
            responseDTO.setModified(true);
        } catch (Exception e) {
            responseDTO.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setModified(false);
        } finally {
            return responseDTO;
        }
    }

    @Override
    public UserAPIResponse findUser(UserSearchDTO userSearch) {
        UserAPIResponse responseDTO = new UserAPIResponse();
        try {
            UserResponseDTO updatedUserResponseDTO = userManagementService.findUserByNameSurnameAndCellNumber(userSearch);
            responseDTO.setData(updatedUserResponseDTO);
            responseDTO.setResponseCode(HttpStatus.FOUND.value());
            responseDTO.setHttpStatus(HttpStatus.FOUND);
            responseDTO.setMessage("User found.");
            responseDTO.setModified(false);
        } catch (Exception e) {
            responseDTO.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setModified(false);
        } finally {
            return responseDTO;
        }
    }
}
