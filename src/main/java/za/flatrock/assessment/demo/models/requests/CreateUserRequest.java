package za.flatrock.assessment.demo.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import za.flatrock.assessment.demo.validation.annotation.ValidPhoneNumber;
import za.flatrock.assessment.demo.validation.annotation.ValidRole;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateUserRequest {

    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotBlank(message = "Surname must not be empty")
    private String surname;

    @NotNull(message = "Cell phone number must not be null")
    @ValidPhoneNumber()
    private String cellPhoneNumber;

    @NotNull(message = "Role must not be null")
    @ValidRole()
    private String role;

}
