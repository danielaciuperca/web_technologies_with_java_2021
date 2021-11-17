package com.unibuc.ex1curs6.repository;

import com.unibuc.ex1curs6.model.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Repository
public class BankAccountRepository {
    private List<BankAccount> bankAccounts = new ArrayList<>();

    public BankAccount create(BankAccount bankAccount) {
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    public Optional<BankAccount> getById(String id) {
        return bankAccounts
                .stream()
                .filter(bankAccount -> bankAccount.getId().equals(id))
                .findFirst();
    }

    public List<BankAccount> getByTypeOrBalance(BankAccountType type, Double balance) {
        return bankAccounts
                .stream()
                .filter(bankAccount -> type == null || bankAccount.getType().equals(type))
                .filter(bankAccount -> balance == null || bankAccount.getBalance() == (balance))
                .collect(Collectors.toList());
    }
}
