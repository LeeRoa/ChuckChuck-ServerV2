package cc.repo;

import cc.domain.notice.entity.NoticeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeCommentRepository extends JpaRepository<NoticeComment, Integer> {
}