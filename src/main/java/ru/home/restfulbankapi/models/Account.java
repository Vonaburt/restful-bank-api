package ru.home.restfulbankapi.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {
    private Integer accountNumber;
    private UserInfo userInfo;
    private Currency currency;
    private BigDecimal balance;
}
