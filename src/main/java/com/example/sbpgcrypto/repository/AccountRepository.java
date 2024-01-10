package com.example.sbpgcrypto.repository;

import com.example.sbpgcrypto.entity.Account;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.spi.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value =
            "SELECT pgp_sym_decrypt(account.account_number\\:\\:bytea, 'mysecret') FROM Account account where account.STATUS = 'active' LIMIT ?1"
            , nativeQuery = true
    )
    List<String> findAccountsByReadLimit(@Param("accountReadLimit") int accountReadLimit);

    List<Account> findByAccountType(String type);

    List<Account> findByAccountStatus(String status);
}
