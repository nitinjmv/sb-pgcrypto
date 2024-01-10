package com.example.sbpgcrypto.service;

import com.example.sbpgcrypto.dto.BulkAccountsDto;
import com.example.sbpgcrypto.entity.Account;
import com.example.sbpgcrypto.repository.BulkAccountRepository;
import com.example.sbpgcrypto.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
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
        return accountRepository.findByAccountType(type);
    }

    public List<Account> getAccountsByStatus(String status) {
        return accountRepository.findByAccountStatus(status);
    }

    public List<String> getAccountByReadLimit(int accountReadLimit) {
        return accountRepository.findAccountsByReadLimit(accountReadLimit);
    }
}
