package cc.repo;

import cc.domain.approval.entity.ApprovalLineSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalLineSystemRepository extends JpaRepository<ApprovalLineSystem, Integer> {
}