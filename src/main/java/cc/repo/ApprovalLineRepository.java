package cc.repo;

import cc.domain.approval.entity.ApprovalLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalLineRepository extends JpaRepository<ApprovalLine, Integer> {
}