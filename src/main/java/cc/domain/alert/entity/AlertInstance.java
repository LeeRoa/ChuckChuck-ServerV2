package cc.domain.alert.entity;

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
@Table(name = "alert_instance")
public class AlertInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_instance_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "alert_id", nullable = false)
    private AlertMaster alertId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipient_id", nullable = false)
    private Employee recipientId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "send_id", nullable = false)
    private Employee sendId;

    @Size(max = 500)
    @NotNull
    @Column(name = "alert_title", nullable = false, length = 500)
    private String alertTitle;

    @NotNull
    @ColumnDefault("'N'")
    @Column(name = "is_read", nullable = false)
    private Character isRead;

    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Size(max = 255)
    @Column(name = "spare")
    private String spare;

}