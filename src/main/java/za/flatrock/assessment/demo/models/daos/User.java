package za.flatrock.assessment.demo.models.daos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Users")
@Data
@IdClass(UserId.class)
@Getter
@Setter
@EqualsAndHashCode
public class User {
    private String uniqueUserId;
    @Id
    private String firstName;
    @Id

    private String lastName;
    @Id
    private String cellNumber;
    @ManyToMany
    private Set<Role> roleSet;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "status_id")
    private ProfileStatus profileStatus;

    public User() {
    }

    public User(String firstName, String lastName, String cellNumber, Set<Role> roleSet) {
        this.uniqueUserId = String.valueOf(UUID.randomUUID());
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellNumber = cellNumber;
        this.roleSet = roleSet;
    }
}