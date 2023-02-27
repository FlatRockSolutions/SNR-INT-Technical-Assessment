package za.flatrock.assessment.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.flatrock.assessment.demo.models.enums.RoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "role_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum role;

    public Role(String role) {
        this.role = RoleEnum.getRole(role);
    }
}
