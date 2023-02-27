package za.flatrock.assessment.demo.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private String surname;
    private String cellPhoneNumber;
    private String role;

}
