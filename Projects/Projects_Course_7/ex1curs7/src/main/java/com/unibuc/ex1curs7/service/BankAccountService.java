package com.unibuc.ex1curs7.service;

import com.unibuc.ex1curs7.exception.BankAccountNotFoundException;
import com.unibuc.ex1curs7.model.BankAccount;
import com.unibuc.ex1curs7.model.BankAccountType;
import com.unibuc.ex1curs7.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {
    private BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount createBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.createBankAccount(bankAccount);
    }

    public BankAccount getBankAccount(String id) {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.getBankAccount(id);
        if(bankAccountOptional.isPresent()) {
            return bankAccountOptional.get();
        } else {
            throw new BankAccountNotFoundException();
        }
    }

    public List<BankAccount> getBankAccountsBy(BankAccountType type, Double balance) {
        return bankAccountRepository.getBankAccountsBy(type, balance);
    }
}
