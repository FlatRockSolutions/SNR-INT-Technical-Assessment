package za.flatrock.assessment.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import za.flatrock.assessment.demo.models.SearchCriteria;
import za.flatrock.assessment.demo.models.requests.CreateUserRequest;
import za.flatrock.assessment.demo.models.responses.api.ApiResponse;
import za.flatrock.assessment.demo.services.UserServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Slf4j
@Validated
@RestController
@RequestMapping(value = "v1/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse> search(
            @Valid @NotBlank(message = "Name must not be blank") @RequestParam(name = "name") String name,
            @Valid @NotBlank(message = "Surname must not be blank") @RequestParam(name = "surname")  String surname,
            @Valid @NotBlank(message = "Cell phone number must not be blank") @RequestParam(name = "cell_phone_number") String cellphoneNumber) {
        log.info("Received search request with parameters name: {}, surname: {}, cell_phone_number: {}", name, surname, cellphoneNumber);
        ApiResponse apiResponse = userServiceImpl.search(new SearchCriteria(name,surname,cellphoneNumber));
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping()
    public ResponseEntity<ApiResponse> createUser(@RequestBody @Valid CreateUserRequest createUserRequest, Errors validationError) {
        log.info("Received CreateUserRequest {}", createUserRequest);
        ApiResponse apiResponse = userServiceImpl.create(createUserRequest, validationError);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@Valid @NotNull @PathVariable(name = "id") Long id) {
        ApiResponse apiResponse = userServiceImpl.deleteUser(id);
        return ResponseEntity.ok(apiResponse);
    }
}
