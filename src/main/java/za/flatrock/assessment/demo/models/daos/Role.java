package za.flatrock.assessment.demo.models.daos;

import lombok.*;
import za.flatrock.assessment.demo.models.enums.RoleEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Role")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@EqualsAndHashCode
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @ManyToMany
    private Set<User> userSet;

}
