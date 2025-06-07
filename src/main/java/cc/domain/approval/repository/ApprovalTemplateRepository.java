package cc.domain.approval.repository;

import cc.domain.approval.entity.ApprovalTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalTemplateRepository extends JpaRepository<ApprovalTemplate, Integer> {
}