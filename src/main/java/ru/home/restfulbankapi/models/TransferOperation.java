package ru.home.restfulbankapi.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransferOperation {
    private Integer operationId;
    private LocalDateTime operationDateTime;
    private Integer fromAccountNumber;
    private Integer toAccountNumber;
    private Integer balance;
    private OperationStatus operationStatus;
}
