package za.flatrock.assessment.demo.models.daos;

import lombok.*;
import za.flatrock.assessment.demo.models.enums.RecordStatusEnum;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Table(name = "RecordStatus")
@AllArgsConstructor
@Data
@Setter
@Getter
@EqualsAndHashCode
public class ProfileStatus {

    private final AtomicLong counter = new AtomicLong(0);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RecordStatusEnum status;

    public ProfileStatus() {
//        id = counter.incrementAndGet();
    }

}
