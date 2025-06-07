package cc.domain.workpolicy.repository;

import cc.domain.workpolicy.entity.WorkPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkPolicyRepository extends JpaRepository<WorkPolicy, Integer> {
}