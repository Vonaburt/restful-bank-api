package ru.home.restfulbankapi.controllers;

import lombok.extern.java.Log;
import ru.home.restfulbankapi.models.Account;
import ru.home.restfulbankapi.models.Currency;
import ru.home.restfulbankapi.models.UserInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

@Path("account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Log
public class AccountController {

    @POST
    @Path("/createAccount")
    public Response createAccount(UserInfo userInfo, @QueryParam("currency") Currency currency) {
        if (userInfo == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("UserInfo is null").build();
        }

        if (currency == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Currency is null").build();
        }

        Account account = new Account(userInfo, currency, new BigDecimal(0));
        log.info(String.format("/createAccount : %s", account.toString()));
        return Response.ok(account).build();
    }
}