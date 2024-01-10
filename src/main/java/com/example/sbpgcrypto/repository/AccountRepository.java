package com.example.sbpgcrypto.repository;

import com.example.sbpgcrypto.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(
       "SELECT account.account_number " +
               "FROM Account account " +
               "where account.accountStatus = 'active' " +
               "or account.accountStatus = 'inactive' "
//               "and rownum <= accountReadLimit"
    )
    List<String> findAccountsByReadLimit(@Param("accountReadLimit") Long accountReadLimit);

    List<Account> findByAccountType(String type);

    List<Account> findByAccountStatus(String status);
}
