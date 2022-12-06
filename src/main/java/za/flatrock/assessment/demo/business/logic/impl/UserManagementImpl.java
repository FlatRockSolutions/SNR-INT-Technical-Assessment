package za.flatrock.assessment.demo.business.logic.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.flatrock.assessment.demo.business.logic.UserManagementService;
import za.flatrock.assessment.demo.business.validators.RequestValidatorService;
import za.flatrock.assessment.demo.exceptions.CellNumberValidationException;
import za.flatrock.assessment.demo.exceptions.UnableToPerformActionException;
import za.flatrock.assessment.demo.exceptions.UserNotFoundException;
import za.flatrock.assessment.demo.models.daos.ProfileStatus;
import za.flatrock.assessment.demo.models.daos.Role;
import za.flatrock.assessment.demo.models.daos.User;
import za.flatrock.assessment.demo.models.dtos.role.RoleUpdateDTO;
import za.flatrock.assessment.demo.models.dtos.user.CreateUserRequestDTO;
import za.flatrock.assessment.demo.models.dtos.user.DeleteUserResponseDTO;
import za.flatrock.assessment.demo.models.dtos.user.UserResponseDTO;
import za.flatrock.assessment.demo.models.dtos.user.UserSearchDTO;
import za.flatrock.assessment.demo.models.enums.RecordStatusEnum;
import za.flatrock.assessment.demo.models.enums.RoleEnum;
import za.flatrock.assessment.demo.models.repositories.RoleRepository;
import za.flatrock.assessment.demo.models.repositories.StatusRepository;
import za.flatrock.assessment.demo.models.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserManagementImpl implements UserManagementService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StatusRepository statusRepository;
    private final RequestValidatorService requestValidatorService;

    @Autowired
    public UserManagementImpl(UserRepository userRepository, RoleRepository roleRepository, StatusRepository statusRepository,
                              RequestValidatorService requestValidatorService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.statusRepository = statusRepository;
        this.requestValidatorService = requestValidatorService;
    }

    @Override
    public UserResponseDTO createUser(CreateUserRequestDTO createUserRequest) throws CellNumberValidationException {
        requestValidatorService.validateRequest(createUserRequest);
        List<RoleEnum> roleENUMS = createRoleEnumList(createUserRequest.getRoleNames());
        ProfileStatus profileStatus = statusRepository.findRecordStatusByStatus(RecordStatusEnum.ACTIVE);

        List<Role> roles = roleRepository.findByNameIn(roleENUMS);
        User user = new User(
                createUserRequest.getName(),
                createUserRequest.getSurname(),
                createUserRequest.getCellNumber(), null
        );

        if (!roles.isEmpty()) {
            user.setRoleSet(new HashSet<>(roles));
        }

        if (profileStatus != null) {
            user.setProfileStatus(profileStatus);
        }
        User savedUser = userRepository.save(user);

        return createUserResponse(savedUser);
    }

    @Override
    public UserResponseDTO updateRole(RoleUpdateDTO roleUpdate) throws UnableToPerformActionException {
        User savedUser = userRepository.findUserByUniqueUserId(roleUpdate.getUniqueId());
        Role updatedRole = roleRepository.findRoleByName(RoleEnum.valueOf(roleUpdate.getRole()));
        Set<Role> savedUserRoles = savedUser.getRoleSet();
        if (updatedRole.getName() == null || savedUserRoles.contains(updatedRole.getName())) {
            throw new UnableToPerformActionException("Role update failed.");
        }
        savedUserRoles.add(updatedRole);
        savedUser.setRoleSet(savedUserRoles);
        userRepository.save(savedUser);
        return createUserResponse(savedUser);
    }

    @Override
    public UserResponseDTO findUserByNameSurnameAndCellNumber(UserSearchDTO userSearch) throws UserNotFoundException {
        User savedUser = userRepository.findUserByFirstNameAndLastNameAndCellNumber(
                userSearch.getFirstName(), userSearch.getLastName(), userSearch.getCellNumber());
        if (savedUser == null) {
            logger.error("User not found.");
            throw new UserNotFoundException("User not found.");
        }

        return new UserResponseDTO(savedUser.getFirstName(), savedUser.getLastName(),
                savedUser.getCellNumber());

    }

    @Override
    public DeleteUserResponseDTO deleteUserById(String id) {
        try {
            ProfileStatus newStatus = new ProfileStatus();
            newStatus.setId(1L);
            newStatus.setStatus(RecordStatusEnum.INACTIVE);
            User userToBeDeleted = userRepository.findUserByUniqueUserId(id);
            ProfileStatus currentStatus = userToBeDeleted.getProfileStatus();
            if (!currentStatus.getStatus().equals(RecordStatusEnum.ACTIVE)) {
                logger.error("User has already been deleted.");
                return new DeleteUserResponseDTO(false, "User has already been deleted.");
            }
            userToBeDeleted.setProfileStatus(newStatus);
            userRepository.saveAndFlush(userToBeDeleted);
            return new DeleteUserResponseDTO(true, "User successfully deleted.");
        } catch (Exception e) {
            logger.error("Failed to delete due to e: ", e);
            return new DeleteUserResponseDTO(false, "Failed to delete user.");
        }
    }

    private UserResponseDTO createUserResponse(User user) {
        UserResponseDTO response = new UserResponseDTO();
        response.setName(user.getFirstName());
        response.setSurname(user.getLastName());
        response.setCellNumber(user.getCellNumber());

        return response;
    }

    private List<RoleEnum> createRoleEnumList(List<String> roleNames) {
        List<RoleEnum> createdList = new ArrayList<>();
        for (String role : roleNames) {
            createdList.add(RoleEnum.valueOf(role));
        }
        return createdList;
    }

}
