package com.example.sbpgcrypto.controller;

import com.example.sbpgcrypto.dto.BulkAccountsDto;
import com.example.sbpgcrypto.entity.Account;
import com.example.sbpgcrypto.service.AccountService;
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
            @RequestParam("type") String type,
            @RequestParam("status") String status){
        accountService.bulkUpload(bulkAccountsDto, type, status);
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

//    @GetMapping("{limit}")
//    public List<String> getAccountsByReadLimit(
//            @PathVariable("limit") int limit){
//        return accountService.getAccountByReadLimit(limit);
//    }

    @GetMapping("limit")
    public List<String> getAccountsByReadLimitRequestParam(
            @RequestParam Long accountReadLimit){
        return accountService.getAccountByReadLimit(accountReadLimit);
    }

}
