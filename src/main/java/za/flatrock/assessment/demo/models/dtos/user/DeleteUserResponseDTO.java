package za.flatrock.assessment.demo.models.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteUserResponseDTO {

    private Boolean success;
    private String message;

}
