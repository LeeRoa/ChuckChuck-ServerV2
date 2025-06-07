package cc.domain.notice.entity;

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
@Table(name = "cc_notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_no", nullable = false)
    private Long id;

    @Size(max = 200)
    @NotNull
    @Column(name = "notice_title", nullable = false, length = 200)
    private String noticeTitle;

    @NotNull
    @Lob
    @Column(name = "notice_content", nullable = false)
    private String noticeContent;

    @NotNull
    @ColumnDefault("'Y'")
    @Column(name = "allow_comment", nullable = false)
    private YesNo allowComment;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @NotNull
    @ColumnDefault("'Y'")
    @Column(name = "notification_yn", nullable = false)
    private YesNo notificationYn;

    @NotNull
    @ColumnDefault("'N'")
    @Column(name = "delete_yn", nullable = false)
    private YesNo deleteYn;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "view_count", nullable = false)
    private Integer viewCount;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creator_id", nullable = false)
    private Employee creatorId;

    @Size(max = 255)
    @Column(name = "spare")
    private String spare;

}