package cc.domain.rank.entity;

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
@Table(name = "cc_rank")
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rank_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_rank_id")
    private Rank parentRank;

    @Size(max = 100)
    @NotNull
    @Column(name = "rank_name", nullable = false, length = 100)
    private String rankName;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "display_order", nullable = false)
    private Integer displayOrder;

    @NotNull
    @ColumnDefault("'Y'")
    @Column(name = "is_active", nullable = false)
    private YesNo isActive;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Size(max = 255)
    @Column(name = "spare")
    private String spare;

}