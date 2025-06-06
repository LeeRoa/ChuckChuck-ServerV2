package cc.domain.accountrequest.repository;

import cc.domain.accountrequest.entity.AccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRequestRepository extends JpaRepository<AccountRequest, Integer> {
}