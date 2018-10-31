package ru.home.restfulbankapi.storages;

import ru.home.restfulbankapi.models.TransferOperation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransferOperationsStorage {
    private static List<TransferOperation> operations = Collections.synchronizedList(new ArrayList<>());

    public static void add(TransferOperation operation) {
        operations.add(operation);
    }

    public static void remove(TransferOperation operation) {
        operations.remove(operation);
    }

    public static List<TransferOperation> getAccounts() {
        return operations;
    }
}
