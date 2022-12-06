package za.flatrock.assessment.demo.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAPIResponse {
    private int responseCode;
    private HttpStatus httpStatus;
    private String message;
    private boolean isModified;
    private Object data;
}