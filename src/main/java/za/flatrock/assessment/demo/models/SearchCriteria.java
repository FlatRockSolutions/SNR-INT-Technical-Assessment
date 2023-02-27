package za.flatrock.assessment.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SearchCriteria {

    private String name;
    private String surname;
    private String cellPhoneNumber;
}
