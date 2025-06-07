package cc.domain.workpolicy.entity;

import cc.domain.employee.entity.Employee;
import cc.entity.YesNo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "work_policy")
public class WorkPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_policy_id", nullable = false)
    private Long id;

    @Size(max = 10)
    @NotNull
    @Column(name = "day_of_week", nullable = false, length = 10)
    private String dayOfWeek;

    @NotNull
    @Column(name = "work_yn", nullable = false)
    private YesNo workYn;

    @Column(name = "work_start_time")
    private LocalTime workStartTime;

    @Column(name = "work_end_time")
    private LocalTime workEndTime;

    @NotNull
    @Column(name = "lunch_start_time", nullable = false)
    private LocalTime lunchStartTime;

    @NotNull
    @Column(name = "lunch_end_time", nullable = false)
    private LocalTime lunchEndTime;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employeeId;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ColumnDefault("current_timestamp()")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Size(max = 255)
    @Column(name = "spare")
    private String spare;

}