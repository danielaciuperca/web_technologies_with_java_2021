package com.unibuc.ex1curs10.repository;

import com.unibuc.ex1curs10.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.*;

import javax.persistence.*;
import java.util.*;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    List<BankAccount> findByType(BankAccountType type); // 1. query from method name

    //NATIVE QUERY - select avg(ba.balance) from bankaccounts ba where ba.type = :type
    @Query("select avg(ba.balance) from BankAccount ba where ba.type = :type") //2. JPQL - queries on entities
    double getAverageBalance(BankAccountType type);

    @Query(nativeQuery = true,
            value = "select avg(ba.balance) from bankaccounts ba where ba.type = :type")
    double getAverageBalanceWithNativeQuery(BankAccountType type);

    @Modifying
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(nativeQuery = true,
            value = "update bankaccounts ba set ba.balance = ba.balance + :amount where ba.id = :id") //3. native query
    void modifyBalance(double amount, long id);
}
