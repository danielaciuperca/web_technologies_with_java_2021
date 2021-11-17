package com.unibuc.ex1curs7.controller;

import com.unibuc.ex1curs7.dto.*;
import com.unibuc.ex1curs7.mapper.*;
import com.unibuc.ex1curs7.model.*;
import com.unibuc.ex1curs7.service.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.net.*;
import java.util.*;

@RestController
@Validated
@RequestMapping("/bankAccounts")
public class BankAccountController {

    private BankAccountService bankAccountService;
    private BankAccountMapper bankAccountMapper;

    public BankAccountController(BankAccountService bankAccountService, BankAccountMapper bankAccountMapper) {
        this.bankAccountService = bankAccountService;
        this.bankAccountMapper = bankAccountMapper;
    }

    @PostMapping
    public ResponseEntity<BankAccount> createBankAccount(
            @Valid
            @RequestBody
                    BankAccountRequest bankAccountRequest) {
        BankAccount bankAccount = bankAccountMapper.bankAccountRequestToBankAccount(bankAccountRequest);
        BankAccount createdBankAccount = bankAccountService.createBankAccount(bankAccount);
        return ResponseEntity
                .created(URI.create("/bankAccounts/" + createdBankAccount.getId()))
                .body(createdBankAccount);
    }

    @GetMapping("/{id}")
    public BankAccount getBankAccount(@PathVariable String id) {
        return bankAccountService.getBankAccount(id);
    }

    @GetMapping
    public List<BankAccount> getAllBankAccounts(
            @RequestParam(required = false) BankAccountType type,
            @RequestParam(required = false) Double balance) {
        return bankAccountService.getBankAccountsBy(type, balance);
    }
}
