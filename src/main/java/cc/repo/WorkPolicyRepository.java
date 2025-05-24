package cc.repo;

import cc.entity.WorkPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkPolicyRepository extends JpaRepository<WorkPolicy, Integer> {
}