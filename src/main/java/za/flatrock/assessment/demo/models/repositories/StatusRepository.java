package za.flatrock.assessment.demo.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.flatrock.assessment.demo.models.daos.ProfileStatus;
import za.flatrock.assessment.demo.models.enums.RecordStatusEnum;

@Repository
public interface StatusRepository extends JpaRepository<ProfileStatus, Long> {
    ProfileStatus findRecordStatusByStatus(RecordStatusEnum status);
}
