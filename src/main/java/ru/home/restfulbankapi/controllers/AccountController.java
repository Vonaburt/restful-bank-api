package ru.home.restfulbankapi.controllers;

import lombok.extern.java.Log;
import ru.home.restfulbankapi.models.Account;
import ru.home.restfulbankapi.models.Currency;
import ru.home.restfulbankapi.models.UserInfo;
import ru.home.restfulbankapi.storages.AccountsStorage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Optional;

@Path("account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Log
public class AccountController {

    @POST
    @Path("/createAccount")
    public Response createAccount(UserInfo userInfo, @QueryParam("currency") Currency currency) {
        if (userInfo == null || currency == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(String.format("UserInfo is %s, Currency is %s", userInfo, currency)).build();
        }

        Account account = new Account(userInfo, currency, new BigDecimal(0));
        AccountsStorage.add(account);

        log.info(String.format("/createAccount : %s", account.toString()));
        return Response.ok(account).build();
    }

    @DELETE
    @Path("/deleteAccount")
    public Response deleteAccount(@QueryParam("accountNumber") Integer accountNumber) {
        if (accountNumber == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Account number is null").build();
        }

        Optional<Account> accountToDelete = AccountsStorage.getAccounts().stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber)).findFirst();

        if (accountToDelete.isPresent()) {
            log.info(String.format("/deleteAccount : %s", accountToDelete.get()));
            AccountsStorage.remove(accountToDelete.get());
            return Response.ok(accountToDelete.get()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(String.format("Couldn`t to delete account with account number %s", accountNumber)).build();
        }
    }

    @GET
    @Path("/getAccountByNumber")
    public Response getAccount(@QueryParam("accountNumber") Integer accountNumber) {
        if (accountNumber == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Account number is null").build();
        }

        Optional<Account> accountToGet = AccountsStorage.getAccounts().stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber)).findFirst();

        if (accountToGet.isPresent()) {
            log.info(String.format("/getAccount : %s", accountToGet.get()));
            return Response.ok(accountToGet.get()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(String.format("Couldn`t to get account with account number %s", accountNumber)).build();
        }
    }

    @GET
    @Path("/getAccountByInfo")
    public Response getAccount(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName) {
        if (firstName.isEmpty() || lastName.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(String.format("Invalid data for account getting: firstName is '%s', lastName is '%s'", firstName, lastName)).build();
        }

        Optional<Account> accountToGet = AccountsStorage.getAccounts().stream()
                .filter(account -> account.getUserInfo().getFirstName().equals(firstName) && account.getUserInfo().getLastName().equals(lastName)).findFirst();

        if (accountToGet.isPresent()) {
            log.info(String.format("/getAccount : %s", accountToGet.get()));
            return Response.ok(accountToGet.get()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(String.format("Couldn`t to get account with account firstName %s and lastName %s", firstName, lastName)).build();
        }
    }
}