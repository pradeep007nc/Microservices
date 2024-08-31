package dev.pradeep.Microservices.respository;

import dev.pradeep.Microservices.models.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountsDao extends CrudRepository<Accounts, Long> {
    Optional<Accounts> findByCustomerId(Long id);

    @Transactional
    @Modifying
    void deleteByCustomerId(Long id);
}
