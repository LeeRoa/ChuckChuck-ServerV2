package cc.repo;

import cc.domain.employee.entity.EmpCommute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpCommuteRepository extends JpaRepository<EmpCommute, Integer> {
}