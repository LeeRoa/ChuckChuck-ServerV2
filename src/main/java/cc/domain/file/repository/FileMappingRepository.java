package cc.domain.file.repository;

import cc.domain.file.entity.FileMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMappingRepository extends JpaRepository<FileMapping, Integer> {
}