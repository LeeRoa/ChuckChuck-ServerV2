package cc.domain.department.repository;

import cc.domain.department.entity.DepartmentLeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentLeaderRepository extends JpaRepository<DepartmentLeader, Integer> {
}