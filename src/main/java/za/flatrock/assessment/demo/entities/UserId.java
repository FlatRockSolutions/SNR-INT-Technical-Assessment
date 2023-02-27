package za.flatrock.assessment.demo.entities;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
public class UserId implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private String cellPhoneNumber;

    public UserId(String name, String surname, String cellPhoneNumber) {
        this.name = name;
        this.surname = surname;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(id, userId.id) && Objects.equals(name, userId.name) && Objects.equals(surname, userId.surname) && Objects.equals(cellPhoneNumber, userId.cellPhoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, cellPhoneNumber);
    }
}
