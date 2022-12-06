package za.flatrock.assessment.demo.models.dtos.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleUpdateDTO {
    private String uniqueId;
    private String role;
}
