package cc.repo;

import cc.entity.FileMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMappingRepository extends JpaRepository<FileMapping, Integer> {
}