package cc.repo;

import cc.entity.ApprovalTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalTemplateRepository extends JpaRepository<ApprovalTemplate, Integer> {
}