package com.example.sbpgcrypto.repository;

import com.example.sbpgcrypto.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value =
            "SELECT pgp_sym_decrypt(account.ACCOUNT_NUMBER\\:\\:bytea, '${pgcrypto.secret}') FROM ACCT_TABLE account where account.STS = 'active' LIMIT ?1"
            , nativeQuery = true
    )
    List<String> findAccountsByReadLimit(@Param("accountReadLimit") Long accountReadLimit);

    List<Account> findByType(String type);

    List<Account> findByStatus(String status);
}
