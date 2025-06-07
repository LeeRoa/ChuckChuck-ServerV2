package cc.domain.schedules.repository;

import cc.domain.schedules.entity.ScheduleGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleGroupMemberRepository extends JpaRepository<ScheduleGroupMember, Integer> {
}