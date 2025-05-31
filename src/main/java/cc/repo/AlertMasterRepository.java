package cc.repo;

import cc.domain.alert.entity.AlertMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertMasterRepository extends JpaRepository<AlertMaster, Integer> {
}