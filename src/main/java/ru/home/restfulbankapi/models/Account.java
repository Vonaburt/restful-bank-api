package ru.home.restfulbankapi.models;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Account {
    private static AtomicInteger count = new AtomicInteger(0);

    private Integer accountNumber;
    private UserInfo userInfo;
    private Currency currency;
    private BigDecimal balance;
    private String accountCreatedDate;

    public Account() {
    }

    public Account(UserInfo userInfo, Currency currency, BigDecimal balance) {
        this.userInfo = userInfo;
        this.currency = currency;
        this.balance = balance;
        this.accountNumber = count.incrementAndGet();
        this.accountCreatedDate = LocalDateTime.now().toString();
    }
}