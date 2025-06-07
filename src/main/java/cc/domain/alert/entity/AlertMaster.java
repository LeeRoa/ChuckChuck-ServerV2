package cc.domain.alert.entity;

import cc.domain.code.entity.Code;
import cc.domain.employee.entity.Employee;
import cc.domain.rank.entity.Rank;
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
@Table(name = "alert_master")
public class AlertMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "alert_type", nullable = false)
    private Code alertType;

    @Size(max = 500)
    @NotNull
    @Column(name = "alert_content", nullable = false, length = 500)
    private String alertContent;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "send_type", nullable = false)
    private Code sendType;

    @NotNull
    @Column(name = "enabled_yn", nullable = false)
    private YesNo enabledYn;

    @Lob
    @Column(name = "alert_template")
    private String alertTemplate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "view_level", nullable = false)
    private Rank viewLevel;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creator_id", nullable = false)
    private Employee creatorId;

    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Size(max = 255)
    @Column(name = "spare")
    private String spare;

}