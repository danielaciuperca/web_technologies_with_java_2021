package com.unibuc.ex1curs6.controller;

import com.unibuc.ex1curs6.model.*;
import com.unibuc.ex1curs6.service.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.*;
import java.util.*;

@RestController
@RequestMapping("/bankAccounts") //the root path for all operations done with bank accounts
public class BankAccountController {

    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    /*
        TODO
        1. create bank account
        2. retrieve bank account by id
        3. retrieve bank accounts list by type and / or balance
     */

    @PostMapping
    public ResponseEntity<BankAccount> create(@RequestBody BankAccount bankAccount) {
        BankAccount savedBankAccount = bankAccountService.create(bankAccount);
        return ResponseEntity
                .created(URI.create("/bankAccounts/" + savedBankAccount.getId()))
                .body(savedBankAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getById(@PathVariable String id) {
        Optional<BankAccount> bankAccountOptional = bankAccountService.getById(id);
        if(bankAccountOptional.isPresent()) {
            return ResponseEntity.ok().body(bankAccountOptional.get());
        } {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<BankAccount> getByTypeOrBalance(
            @RequestParam(required = false) BankAccountType type,
            @RequestParam(required = false) Double balance
    ) {
        return bankAccountService.getByTypeOrBalance(type, balance);
    }
}
