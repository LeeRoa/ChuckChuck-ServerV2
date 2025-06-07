package cc.domain.schedules.entity;

import cc.domain.employee.entity.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "schedule_group")
public class ScheduleGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_group_id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "schedule_group_name", nullable = false, length = 100)
    private String scheduleGroupName;

    @Size(max = 255)
    @Column(name = "schedule_group_description")
    private String scheduleGroupDescription;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ColumnDefault("current_timestamp()")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creator_id", nullable = false)
    private Employee creatorId;

    @Size(max = 255)
    @Column(name = "spare")
    private String spare;

}