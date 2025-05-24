package cc.repo;

import cc.entity.ScheduleGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleGroupMemberRepository extends JpaRepository<ScheduleGroupMember, Integer> {
}