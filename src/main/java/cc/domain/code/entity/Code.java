package cc.domain.code.entity;

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
@Table(name = "cc_code")
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "code_type", nullable = false, length = 50)
    private String codeType;

    @Size(max = 100)
    @NotNull
    @Column(name = "code_name", nullable = false, length = 100)
    private String codeName;

    @NotNull
    @Column(name = "display_order", nullable = false)
    private Integer displayOrder;

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