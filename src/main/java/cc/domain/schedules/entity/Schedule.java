package cc.domain.schedules.entity;

import cc.domain.code.entity.Code;
import cc.domain.employee.entity.Employee;
import cc.entity.YesNo;
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
@Table(name = "cc_schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "schedule_name", nullable = false, length = 100)
    private String scheduleName;

    @Lob
    @Column(name = "schedule_content")
    private String scheduleContent;

    @NotNull
    @Column(name = "schedule_start_dt", nullable = false)
    private LocalDateTime scheduleStartDt;

    @NotNull
    @Column(name = "schedule_end_dt", nullable = false)
    private LocalDateTime scheduleEndDt;

    @Size(max = 255)
    @Column(name = "schedule_place")
    private String schedulePlace;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ColumnDefault("current_timestamp()")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @NotNull
    @ColumnDefault("'N'")
    @Column(name = "allday_yn", nullable = false)
    private YesNo alldayYn;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_group_id", nullable = false)
    private ScheduleGroup scheduleGroupId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creator_id", nullable = false)
    private Employee creatorId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_type", nullable = false)
    private Code scheduleType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status", nullable = false)
    private Code status;

    @Size(max = 255)
    @Column(name = "spare")
    private String spare;

}