package za.flatrock.assessment.demo.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_table")
@IdClass(UserId.class)
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Id
    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "surname")
    private String surname;

    @Id
    @Column(name = "cell_phone_number")
    private String cellPhoneNumber;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "role_id")
    private Role role;

    public User(String name, String surname, String cellPhoneNumber, Role role) {
        this.name = name;
        this.surname = surname;
        this.cellPhoneNumber = cellPhoneNumber;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}
