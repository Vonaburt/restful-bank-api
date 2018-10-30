package ru.home.restfulbankapi.storages;

import ru.home.restfulbankapi.models.Account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountsStorage {
    private static List<Account> accounts = Collections.synchronizedList(new ArrayList<>());

    public static void add(Account account) {
        accounts.add(account);
    }

    public static void remove(Account account) {
        accounts.remove(account);
    }

    public static List<Account> getAccounts() {
        return accounts;
    }
}
