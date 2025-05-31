package cc.repo;

import cc.domain.employee.entity.EmpLeave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpLeaveRepository extends JpaRepository<EmpLeave, Integer> {
}