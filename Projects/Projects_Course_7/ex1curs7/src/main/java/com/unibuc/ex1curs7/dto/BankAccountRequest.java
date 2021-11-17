package com.unibuc.ex1curs7.dto;

import com.unibuc.ex1curs7.model.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.*;

import static com.unibuc.ex1curs7.model.Pattern.*;

public class BankAccountRequest {
    @NotBlank(message = "Account number must be defined")
    private String accountNumber;
    @NotNull
    @Min(0)
    private double balance;
    @NotNull
    private BankAccountType type;
    @NotBlank
    @Pattern(regexp = VISA_CREDIT_CARD)
    private String cardNumber;

    public BankAccountRequest() {
    }

    public BankAccountRequest(String accountNumber,
                              double balance, BankAccountType type, String cardNumber) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
        this.cardNumber = cardNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public BankAccountType getType() {
        return type;
    }

    public void setType(BankAccountType type) {
        this.type = type;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
