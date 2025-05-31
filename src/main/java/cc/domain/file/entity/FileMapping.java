package cc.domain.file.entity;

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
@Table(name = "file_mapping")
public class FileMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_mapping_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "file_no", nullable = false)
    private File fileNo;

    @NotNull
    @Column(name = "target_type", nullable = false)
    private YesNo targetType;

    @NotNull
    @Column(name = "target_id", nullable = false)
    private Integer targetId;

    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Size(max = 255)
    @Column(name = "spare")
    private String spare;

}