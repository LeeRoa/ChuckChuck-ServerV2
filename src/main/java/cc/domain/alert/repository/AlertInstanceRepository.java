package cc.domain.alert.repository;

import cc.domain.alert.entity.AlertInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertInstanceRepository extends JpaRepository<AlertInstance, Integer> {
}