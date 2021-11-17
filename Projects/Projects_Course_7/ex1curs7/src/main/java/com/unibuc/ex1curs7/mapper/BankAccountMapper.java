package com.unibuc.ex1curs7.mapper;

import com.unibuc.ex1curs7.dto.BankAccountRequest;
import com.unibuc.ex1curs7.model.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {

    public BankAccount bankAccountRequestToBankAccount(BankAccountRequest bankAccountRequest) {
        return new BankAccount(bankAccountRequest.getAccountNumber(), bankAccountRequest.getBalance(),
                bankAccountRequest.getType(), bankAccountRequest.getCardNumber());
    }
}
