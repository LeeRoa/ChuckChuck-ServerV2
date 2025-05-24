package cc.repo;

import cc.entity.ScheduleEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleEmployeeRepository extends JpaRepository<ScheduleEmployee, Integer> {
}