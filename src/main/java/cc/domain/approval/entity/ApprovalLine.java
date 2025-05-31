package cc.domain.approval.entity;

import cc.domain.code.entity.Code;
import cc.domain.employee.entity.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "approval_line")
public class ApprovalLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "line_type", nullable = false)
    private Code lineType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doc_no", nullable = false)
    private Approval docNo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "approver_id", nullable = false)
    private Employee approverId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_code")
    private Code statusCode;

    @Column(name = "approval_seq")
    private Integer approvalSeq;

    @Column(name = "approval_dt")
    private LocalDateTime approvalDt;

    @Lob
    @Column(name = "approval_comment")
    private String approvalComment;

    @Column(name = "comment_dt")
    private LocalDateTime commentDt;

    @Size(max = 255)
    @Column(name = "spare")
    private String spare;

}