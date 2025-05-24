package cc.repo;

import cc.entity.DepartmentLeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentLeaderRepository extends JpaRepository<DepartmentLeader, Integer> {
}