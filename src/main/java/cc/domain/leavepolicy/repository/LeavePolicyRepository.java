package cc.domain.leavepolicy.repository;

import cc.domain.leavepolicy.entity.LeavePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeavePolicyRepository extends JpaRepository<LeavePolicy, Integer> {
}