package cc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "emp_leave")
public class EmpLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_seq", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "leave_type", nullable = false)
    private Code leaveType;

    @NotNull
    @Column(name = "leave_use_days", nullable = false)
    private Integer leaveUseDays;

    @NotNull
    @Column(name = "leave_start_dt", nullable = false)
    private LocalDateTime leaveStartDt;

    @NotNull
    @Column(name = "leave_end_dt", nullable = false)
    private LocalDateTime leaveEndDt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employeeId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doc_no", nullable = false)
    private Approval docNo;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ColumnDefault("current_timestamp()")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}