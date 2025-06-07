package cc.test.roa.repository;

import cc.test.roa.entity.Roa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoaRepository extends CrudRepository<Roa, Long> {
}
