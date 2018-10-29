package ru.home.restfulbankapi.controllers;

import ru.home.restfulbankapi.models.Account;
import ru.home.restfulbankapi.models.Currency;
import ru.home.restfulbankapi.models.UserInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

@Path("myresource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountController {

    @POST
    @Path("/createAccount")
    public Response createAccount(UserInfo userInfo, @QueryParam("currency") Currency currency) {
        Account account = new Account(userInfo, currency, new BigDecimal(0));
        System.out.println(account.toString());

        return Response.ok(account).build();
    }

    @GET
    @Path("/hello")
    public Response test() {
        return Response.ok("Hello").build();
    }
}