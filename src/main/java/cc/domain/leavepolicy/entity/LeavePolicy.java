package cc.domain.leavepolicy.entity;

import cc.domain.code.entity.Code;
import cc.domain.company.entity.Company;
import cc.domain.rank.entity.Rank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "leave_policy")
public class LeavePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_policy_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "leave_type", nullable = false)
    private Code leaveType;

    @NotNull
    @Column(name = "leave_provide_days", nullable = false)
    private Integer leaveProvideDays;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Code roleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rank_id")
    private Rank rankId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_no", referencedColumnName = "biz_no")
    private Company companyNo;

    @Column(name = "effective_start")
    private LocalDate effectiveStart;

    @Column(name = "effective_end")
    private LocalDate effectiveEnd;

    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ColumnDefault("current_timestamp()")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Size(max = 255)
    @Column(name = "spare")
    private String spare;

}