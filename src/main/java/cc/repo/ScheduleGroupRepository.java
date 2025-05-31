package cc.repo;

import cc.domain.schedules.entity.ScheduleGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleGroupRepository extends JpaRepository<ScheduleGroup, Integer> {
}