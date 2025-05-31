package cc.domain.file.entity;

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
@Table(name = "cc_file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_no", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "origin_name", nullable = false)
    private String originName;

    @Size(max = 255)
    @Column(name = "stored_name")
    private String storedName;

    @NotNull
    @Column(name = "file_size", nullable = false)
    private Integer fileSize;

    @Size(max = 500)
    @NotNull
    @Column(name = "file_path", nullable = false, length = 500)
    private String filePath;

    @Size(max = 100)
    @NotNull
    @Column(name = "file_content_type", nullable = false, length = 100)
    private String fileContentType;

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