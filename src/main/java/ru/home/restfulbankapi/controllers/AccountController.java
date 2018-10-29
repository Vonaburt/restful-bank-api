package ru.home.restfulbankapi.controllers;

import ru.home.restfulbankapi.models.Account;
import ru.home.restfulbankapi.models.Currency;
import ru.home.restfulbankapi.models.UserInfo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

@Path("myresource")
public class AccountController {

//    @Path("/createAccount")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(UserInfo userInfo, Currency currency) {

        Account account = new Account(userInfo, currency, new BigDecimal(0));
        System.out.println(account.toString());

        return Response.ok().build();
    }

    @GET
    @Path("/hello")
    public Response test() {
        return Response.ok("Hello").build();
    }
}