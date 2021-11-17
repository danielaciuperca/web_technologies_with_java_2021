package com.unibuc.ex1curs6.service;

import com.unibuc.ex1curs6.model.*;
import com.unibuc.ex1curs6.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class BankAccountService {
    private BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount create(BankAccount bankAccount) {
        return bankAccountRepository.create(bankAccount);
    }

    public Optional<BankAccount> getById(String id) {
        return bankAccountRepository.getById(id);
    }

    public List<BankAccount> getByTypeOrBalance(BankAccountType type, Double balance) {
        return bankAccountRepository.getByTypeOrBalance(type, balance);
    }
}
