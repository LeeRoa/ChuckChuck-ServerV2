package cc.entity;

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
@Table(name = "cc_employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 15)
    @NotNull
    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Size(max = 64)
    @NotNull
    @Column(name = "password_hash", nullable = false, length = 64)
    private String passwordHash;

    @Size(max = 50)
    @Column(name = "full_name", length = 50)
    private String fullName;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotNull
    @ColumnDefault("'Y'")
    @Column(name = "is_active", nullable = false)
    private YesNo isActive;

    @NotNull
    @ColumnDefault("'N'")
    @Column(name = "agree_privacy", nullable = false)
    private YesNo agreePrivacy;

    @Size(max = 20)
    @Column(name = "position_code", length = 20)
    private String positionCode;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "login_fail_count", nullable = false)
    private Integer loginFailCount;

    @Column(name = "join_dt")
    private LocalDateTime joinDt;

    @Column(name = "retire_dt")
    private LocalDateTime retireDt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Code roleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rank_id")
    private Rank rankId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_no", referencedColumnName = "biz_no")
    private Company companyNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee managerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_type")
    private Code workType;

    @Column(name = "basic_work_hours")
    private Integer basicWorkHours;

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