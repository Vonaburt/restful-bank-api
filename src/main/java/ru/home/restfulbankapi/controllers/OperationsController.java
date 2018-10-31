package ru.home.restfulbankapi.controllers;

import lombok.extern.java.Log;
import ru.home.restfulbankapi.models.Account;
import ru.home.restfulbankapi.models.OperationStatus;
import ru.home.restfulbankapi.models.TransferOperation;
import ru.home.restfulbankapi.storages.TransferOperationsStorage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Path("operation")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Log
public class OperationsController {

    private final AccountController accountController = new AccountController();

    @POST
    @Path("/transferFromAccount")
    public Response transferFromAccount(@QueryParam("fromAccount") Integer fromAccountNumber, @QueryParam("toAccount") Integer destinationAccountNumber, @QueryParam("amount") BigDecimal amount) {
        if (fromAccountNumber == null || destinationAccountNumber == null || amount.equals(BigDecimal.ZERO)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(String.format("Invalid data for transfer: fromAccountNumber is '%s', destinationAccountNumber is '%s'",
                            fromAccountNumber, destinationAccountNumber)).build();
        }

        Account fromAccount = (Account) accountController.getAccount(fromAccountNumber).getEntity();
        Account destinationAccount = (Account) accountController.getAccount(destinationAccountNumber).getEntity();

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            TransferOperation transferOperation = new TransferOperation(LocalDateTime.now().toString(), fromAccountNumber, destinationAccountNumber, amount, OperationStatus.NOT_ENOUGH_BALANCE);
            TransferOperationsStorage.add(transferOperation);
            log.info(String.format("transfer operation: %s", transferOperation.toString()));

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(String.format("Account does not have enough money for operation. Account balance is %s, amount for transfer is %s",
                            fromAccount.getBalance().toString(), amount.toString())).build();
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        log.info(String.format("Subtracted money from account %s, amount is %s, current balance is %s ",
                fromAccountNumber, amount.toString(), fromAccount.getBalance().toString()));

        destinationAccount.setBalance(destinationAccount.getBalance().add(amount));
        log.info(String.format("Added money to account %s from account %s, amount is %s, current balance is %s ",
                destinationAccount, fromAccountNumber, amount.toString(), fromAccount.getBalance().toString()));

        TransferOperation transferOperation = new TransferOperation(LocalDateTime.now().toString(), fromAccountNumber, destinationAccountNumber, amount, OperationStatus.OK);
        TransferOperationsStorage.add(transferOperation);
        log.info(String.format("Transfer operation: %s", transferOperation.toString()));

        return Response.ok(transferOperation).build();
    }

    @POST
    @Path("/addAmountToAccount")
    public Response addAmountToAccount(@QueryParam("toAccount") Integer destinationAccountNumber, @QueryParam("amount") BigDecimal amount) {
        if (destinationAccountNumber == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Account number is null").build();
        }

        Account destinationAccount = (Account) accountController.getAccount(destinationAccountNumber).getEntity();
        destinationAccount.setBalance(destinationAccount.getBalance().add(amount));

        log.info(String.format("Add amount %s to account %s, new balance is %s",
                amount.toString(), destinationAccountNumber, destinationAccount.getBalance().toString()));

        TransferOperation transferOperation = new TransferOperation(LocalDateTime.now().toString(), 0, destinationAccountNumber, amount, OperationStatus.OK);
        TransferOperationsStorage.add(transferOperation);

        return Response.ok(transferOperation).build();
    }
}