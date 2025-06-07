package cc.domain.approval.repository;

import cc.domain.approval.entity.ApprovalLineSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalLineSystemRepository extends JpaRepository<ApprovalLineSystem, Integer> {
}