package com.example.sbpgcrypto.service;

import com.example.sbpgcrypto.dto.BulkAccountsDto;
import com.example.sbpgcrypto.entity.Account;
import com.example.sbpgcrypto.repository.BulkAccountRepository;
import com.example.sbpgcrypto.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {


    final BulkAccountRepository bulkAccountRepository;

    final AccountRepository accountRepository;

    public void bulkUpload(BulkAccountsDto bulkAccountsDto, String account_type, String status) {
        bulkAccountRepository.saveAccounts(bulkAccountsDto, account_type, status);
    }

    public Account createAccount(Account excludePan) {
        return accountRepository.save(excludePan);
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }


    public List<Account> getAccountsByType(String type) {
        return accountRepository.findByType(type);
    }

    public List<Account> getAccountsByStatus(String status) {
        return accountRepository.findByStatus(status);
    }

    public List<String> getAccountByReadLimit(Long accountReadLimit) {
        return accountRepository.findAccountsByReadLimit(accountReadLimit);
    }

}
