package za.flatrock.assessment.demo.models.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserRequestDTO {

    private String name;
    private String surname;
    private String cellNumber;
    private List<String> roleNames;
}
