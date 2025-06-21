package cc.domain.company.entity;

import cc.domain.code.entity.Code;
import cc.domain.common.YesNo;
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
@Table(name = "cc_company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_no", nullable = false)
    private Long id;

    @Size(max = 20)
    @NotNull
    @Column(name = "biz_no", nullable = false, length = 20)
    private String bizNo;

    @Size(max = 100)
    @NotNull
    @Column(name = "company_name", nullable = false, length = 100)
    private String companyName;

    @Size(max = 255)
    @NotNull
    @Column(name = "company_address", nullable = false)
    private String companyAddress;

    @Size(max = 20)
    @NotNull
    @Column(name = "company_call", nullable = false, length = 20)
    private String companyCall;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ColumnDefault("current_timestamp()")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @NotNull
    @Column(name = "occur_leave_dt", nullable = false)
    private LocalDate occurLeaveDt;

    @NotNull
    @Column(name = "remain_leave_whether", nullable = false)
    private YesNo remainLeaveWhether;

    @NotNull
    @Column(name = "default_leave_cnt", nullable = false)
    private Integer defaultLeaveCnt;

    @NotNull
    @Column(name = "basic_work_hours", nullable = false)
    private Integer basicWorkHours;

    @NotNull
    @Column(name = "overtime_pay_whether", nullable = false)
    private YesNo overtimePayWhether;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "work_type", nullable = false)
    private Code workType;

    @Size(max = 255)
    @Column(name = "spare")
    private String spare;

}