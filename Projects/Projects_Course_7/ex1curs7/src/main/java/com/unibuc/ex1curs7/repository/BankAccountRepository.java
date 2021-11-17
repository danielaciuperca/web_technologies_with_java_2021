package com.unibuc.ex1curs7.repository;

import com.unibuc.ex1curs7.model.BankAccount;
import com.unibuc.ex1curs7.model.BankAccountType;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BankAccountRepository {
    private List<BankAccount> bankAccounts = new ArrayList<>();

    public BankAccount createBankAccount(BankAccount bankAccount) {
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    public Optional<BankAccount> getBankAccount(String id) {
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getId().equals(id))
                .findFirst();
    }

    public List<BankAccount> getBankAccountsBy(BankAccountType type, Double balance) {
        return bankAccounts.stream()
                .filter(bankAccount -> type == null || bankAccount.getType().equals(type))
                .filter(bankAccount -> balance == null || bankAccount.getBalance() == (balance))
                .collect(Collectors.toList());
    }
}
