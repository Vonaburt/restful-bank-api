package ru.home.restfulbankapi.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class TransferOperation {
    private static AtomicInteger count = new AtomicInteger(0);

    private Integer operationId;
    private String operationDateTime;
    private Integer fromAccountNumber;
    private Integer toAccountNumber;
    private BigDecimal amount;
    private OperationStatus operationStatus;

    public TransferOperation() {
    }

    public TransferOperation(String operationDateTime, Integer fromAccountNumber, Integer toAccountNumber, BigDecimal amount, OperationStatus operationStatus) {
        this.operationDateTime = operationDateTime;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
        this.operationStatus = operationStatus;
        this.operationId = count.incrementAndGet();
    }
}
