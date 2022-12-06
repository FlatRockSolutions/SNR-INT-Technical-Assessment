package za.flatrock.assessment.demo.models.daos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class UserId implements Serializable {
    private String firstName;
    private String lastName;
    private String cellNumber;
}
