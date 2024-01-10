package com.example.sbpgcrypto1.controller;

import com.example.sbpgcrypto1.dto.BulkAccountsDto;
import com.example.sbpgcrypto1.entity.Account;
import com.example.sbpgcrypto1.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountController {

    final AccountService accountService;

    @Value("${pgcrypto.secret}")
    String secret;

    @PostMapping("bulk")
    public void uploadAccounts(
            @RequestBody BulkAccountsDto bulkAccountsDto,
            @RequestParam("account_type") String accountType,
            @RequestParam("account_status") String accountStatus){
        accountService.bulkUpload(bulkAccountsDto, accountType, accountStatus);
    }
    @PostMapping
    public Account createAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    @GetMapping("status/{status}")
    public List<Account> getAccountsByStatus(@PathVariable String status){
        return accountService.getAccountsByStatus(status);
    }

    @GetMapping("type/{type}")
    public List<Account> getAccountsByType(@PathVariable String type){
        return accountService.getAccountsByType(type);
    }

    @GetMapping
    public List<Account> getAccounts(){
        return accountService.getAccounts();
    }

    @GetMapping("{limit}")
    public List<String> getAccountsByReadLimit(
            @PathVariable("limit") long limit){
        return accountService.getAccountByReadLimit(limit);
    }

}
