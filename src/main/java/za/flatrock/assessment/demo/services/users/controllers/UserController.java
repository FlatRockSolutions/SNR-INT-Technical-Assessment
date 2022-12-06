package za.flatrock.assessment.demo.services.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.flatrock.assessment.demo.models.dtos.UserAPIResponse;
import za.flatrock.assessment.demo.models.dtos.role.RoleUpdateDTO;
import za.flatrock.assessment.demo.models.dtos.user.CreateUserRequestDTO;
import za.flatrock.assessment.demo.models.dtos.user.UserSearchDTO;
import za.flatrock.assessment.demo.services.users.processor.UserProcessorService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserProcessorService userProcessorService;

    @Autowired
    public UserController(UserProcessorService userProcessorService) {
        this.userProcessorService = userProcessorService;
    }

    @PatchMapping(value = "/create")
    public ResponseEntity<UserAPIResponse> createUser(@RequestBody CreateUserRequestDTO createUserRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userProcessorService.createUser(createUserRequest));
    }


    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<UserAPIResponse> deleteUser(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(userProcessorService.deleteUserById(id));
    }

    @PatchMapping(value = "/modify")
    public ResponseEntity<UserAPIResponse> updateUser(@RequestBody RoleUpdateDTO roleUpdateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userProcessorService.updateRole(roleUpdateDTO));
    }

    @GetMapping(value = "/find")
    public ResponseEntity<UserAPIResponse> findUser(@RequestBody UserSearchDTO searchDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userProcessorService.findUser(searchDTO));
    }
}
