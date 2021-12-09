package com.unibuc.ex1curs10.service;

import com.unibuc.ex1curs10.dto.TransferRequest;
import com.unibuc.ex1curs10.exception.BankAccountNotFoundException;
import com.unibuc.ex1curs10.model.*;
import com.unibuc.ex1curs10.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BankAccountService {
    private BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount createBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public BankAccount getBankAccount(Long id) {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(id);
        if(bankAccountOptional.isPresent()) {
            return bankAccountOptional.get();
        } else {
            throw new BankAccountNotFoundException(id);
        }
    }

    public List<BankAccount> getAllByType(BankAccountType type) {
        return bankAccountRepository.findByType(type);
    }

    public double getAverageBalance(BankAccountType type) {
        return bankAccountRepository.getAverageBalance(type);
    }

    @Transactional
    public void makeBankAccountTransfer(TransferRequest transferRequest) {
//        BankAccount fromBankAccount = getBankAccount(transferRequest.getFromBankAccountId());
//        fromBankAccount.setBalance(fromBankAccount.getBalance() - transferRequest.getAmount());
//        bankAccountRepository.save(fromBankAccount);
//
//        BankAccount toBankAccount = getBankAccount(transferRequest.getToBankAccountId());
//        toBankAccount.setBalance(toBankAccount.getBalance() + transferRequest.getAmount());
//        bankAccountRepository.save(toBankAccount);

        bankAccountRepository.modifyBalance(transferRequest.getAmount(), transferRequest.getToBankAccountId());
        bankAccountRepository.modifyBalance(-transferRequest.getAmount(), transferRequest.getFromBankAccountId());
    }
}
