package cc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cc_company", uniqueConstraints = @UniqueConstraint(columnNames = "biz_no"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_no")
    private Integer companyNo;

    @Column(name = "biz_no", length = 20, nullable = false, unique = true)
    private String bizNo;

    @Column(name = "company_name", length = 100, nullable = false)
    private String companyName;

    @Column(name = "company_address", length = 255, nullable = false)
    private String companyAddress;

    @Column(name = "company_call", length = 20, nullable = false)
    private String companyCall;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "occur_leave_dt", nullable = false)
    private LocalDate occurLeaveDt;

    @Enumerated(EnumType.STRING)
    @Column(name = "remain_leave_whether", length = 1, nullable = false)
    private YesNo remainLeaveWhether;

    @Column(name = "default_leave_cnt", nullable = false)
    private Integer defaultLeaveCnt;

    @Column(name = "basic_work_hours", nullable = false)
    private Integer basicWorkHours;

    @Enumerated(EnumType.STRING)
    @Column(name = "overtime_pay_whether", length = 1, nullable = false)
    private YesNo overtimePayWhether;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_type", nullable = false)
    private Code workType;

    @Column(name = "spare", length = 255)
    private String spare;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
