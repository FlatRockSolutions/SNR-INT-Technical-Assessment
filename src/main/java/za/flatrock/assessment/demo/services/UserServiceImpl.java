package za.flatrock.assessment.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import za.flatrock.assessment.demo.entities.Role;
import za.flatrock.assessment.demo.entities.User;
import za.flatrock.assessment.demo.exceptions.BaseException;
import za.flatrock.assessment.demo.models.SearchCriteria;
import za.flatrock.assessment.demo.models.requests.CreateUserRequest;
import za.flatrock.assessment.demo.models.responses.DeleteUserResponse;
import za.flatrock.assessment.demo.models.responses.UserResponse;
import za.flatrock.assessment.demo.models.responses.api.ApiResponse;
import za.flatrock.assessment.demo.models.responses.api.ApiSearchCriteriaResponse;
import za.flatrock.assessment.demo.models.responses.api.ApiSuccessfulResponse;
import za.flatrock.assessment.demo.repositories.RoleRepository;
import za.flatrock.assessment.demo.repositories.UserRepository;
import za.flatrock.assessment.demo.util.CellPhoneNumberUtil;
import za.flatrock.assessment.demo.validation.CreateUserValidator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final Validator validator;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.validator = new CreateUserValidator();
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public ApiResponse create(CreateUserRequest createUserRequest, Errors validationError) {
        log.info("Proceeding with creating user {}", createUserRequest);

        String cellPhoneNumber = CellPhoneNumberUtil.format(createUserRequest.getCellPhoneNumber());

        Optional<User> user = userRepository.findByUserId(createUserRequest.getName().toUpperCase(), createUserRequest.getSurname().toUpperCase(), cellPhoneNumber);

        validator.validate(user, validationError);
        if (validationError.hasErrors()) {
            throw new BaseException(HttpStatus.NOT_ACCEPTABLE,validationError.getAllErrors());
        }

        Role role = roleRepository.save(new Role(createUserRequest.getRole()));

        User newUser = userRepository.save(new User(
                createUserRequest.getName(),
                createUserRequest.getSurname(),
                cellPhoneNumber,
                role
        ));

        log.info("Successfully saved user to user_table {}", newUser);

        return new ApiSuccessfulResponse(
                new UserResponse(
                        newUser.getId(),
                        newUser.getName(),
                        newUser.getSurname(),
                        newUser.getCellPhoneNumber(),
                        newUser.getRole().getRole().name()));
    }

    @Override
    public ApiResponse deleteUser(Long id) {
        log.info("Proceeding with deleting user with id {}", id);
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()){
            String errorMessage = String.format("User not found for id %s", id);
            log.error(errorMessage);
            throw new BaseException(HttpStatus.NOT_FOUND, errorMessage);
        }

        userRepository.delete(user.get());
        log.info("Successfully deleted user from user_table with id {}", id);
        return new ApiSuccessfulResponse(new DeleteUserResponse(true)) ;
    }

    @Override
    public ApiResponse search(SearchCriteria searchCriteria) {
        Optional<User> user = userRepository.findByUserId(searchCriteria.getName(), searchCriteria.getSurname(), searchCriteria.getCellPhoneNumber());

        if(user.isEmpty()){
            return new ApiSearchCriteriaResponse(Collections.emptyList());
        }

        return new ApiSearchCriteriaResponse(List.of(
                new UserResponse(
                        user.get().getId(),
                        user.get().getName(),
                        user.get().getSurname(),
                        user.get().getCellPhoneNumber(),
                        user.get().getRole().getRole().name())));
    }
}
