package cc.domain.relateddoc.repository;

import cc.domain.relateddoc.entity.RelatedDoc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelatedDocRepository extends JpaRepository<RelatedDoc, Integer> {
}