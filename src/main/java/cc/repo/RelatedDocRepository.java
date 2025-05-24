package cc.repo;

import cc.entity.RelatedDoc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelatedDocRepository extends JpaRepository<RelatedDoc, Integer> {
}