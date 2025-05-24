package cc.entity;

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
@Table(name = "approval_template")
public class ApprovalTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tem_no", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doc_type", nullable = false)
    private Code docType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creator_id", nullable = false)
    private Employee creatorId;

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

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @NotNull
    @ColumnDefault("'N'")
    @Column(name = "delete_yn", nullable = false)
    private YesNo deleteYn;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "view_level", nullable = false)
    private Code viewLevel;

    @Size(max = 255)
    @Column(name = "spare")
    private String spare;

}