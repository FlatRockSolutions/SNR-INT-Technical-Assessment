package za.flatrock.assessment.demo.business.logic;

import za.flatrock.assessment.demo.exceptions.CellNumberValidationException;
import za.flatrock.assessment.demo.exceptions.UnableToPerformActionException;
import za.flatrock.assessment.demo.exceptions.UserNotFoundException;
import za.flatrock.assessment.demo.models.dtos.role.RoleUpdateDTO;
import za.flatrock.assessment.demo.models.dtos.user.CreateUserRequestDTO;
import za.flatrock.assessment.demo.models.dtos.user.DeleteUserResponseDTO;
import za.flatrock.assessment.demo.models.dtos.user.UserResponseDTO;
import za.flatrock.assessment.demo.models.dtos.user.UserSearchDTO;

public interface UserManagementService {
    UserResponseDTO updateRole(RoleUpdateDTO roleUpdate) throws UnableToPerformActionException;

    UserResponseDTO findUserByNameSurnameAndCellNumber(UserSearchDTO userSearch) throws UserNotFoundException;

    UserResponseDTO createUser(CreateUserRequestDTO createUserRequest) throws CellNumberValidationException;

    DeleteUserResponseDTO deleteUserById(String id);
}
