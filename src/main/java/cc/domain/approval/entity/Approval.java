package cc.domain.approval.entity;

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
@Table(name = "cc_approval")
public class Approval {
    @Id
    @Size(max = 50)
    @Column(name = "doc_no", nullable = false, length = 50)
    private String docNo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doc_type", nullable = false)
    private Code docType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creator_id", nullable = false)
    private Employee creatorId;

    @Column(name = "retention_period")
    private LocalDateTime retentionPeriod;

    @Size(max = 200)
    @NotNull
    @Column(name = "doc_title", nullable = false, length = 200)
    private String docTitle;

    @NotNull
    @Lob
    @Column(name = "doc_content", nullable = false)
    private String docContent;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "approval_deadline")
    private LocalDateTime approvalDeadline;

    @NotNull
    @ColumnDefault("'N'")
    @Column(name = "draft_yn", nullable = false)
    private YesNo draftYn;

    @NotNull
    @ColumnDefault("'N'")
    @Column(name = "delete_yn", nullable = false)
    private YesNo deleteYn;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "total_line_count", nullable = false)
    private Integer totalLineCount;

    @Column(name = "current_appro_step")
    private Integer currentApproStep;

    @Size(max = 255)
    @Column(name = "spare")
    private String spare;

}