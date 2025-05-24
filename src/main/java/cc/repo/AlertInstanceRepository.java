package cc.repo;

import cc.entity.AlertInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertInstanceRepository extends JpaRepository<AlertInstance, Integer> {
}