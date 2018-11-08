package ru.home.restfulbankapi;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.home.restfulbankapi.models.Account;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

class AccountControllerTest {

    private static WebTarget target;

    @BeforeAll
    static void runServer() throws IOException {
        Server.runServer(Server.getBASE_URI());
        Client c = ClientBuilder.newClient();
        target = c.target(Server.getBASE_URI());
    }

    @Test
    void testCreateAccount() {
        String accountJsonData = "{\n" +
                "\"firstName\" : \"Kamilla\",\n" +
                "\"lastName\": \"Kamillkina\",\n" +
                "\"age\" : 24\n" +
                "}";

        Response response = target.path("account")
                .path("createAccount")
                .queryParam("currency", "EUR")
                .request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(accountJsonData));

        Account account = response.readEntity(Account.class);
        Assertions.assertEquals("Kamilla", account.getUserInfo().getFirstName(), "FirstName is not equals");
    }


    @AfterAll
    static void stopServer() {
        Server.stopServer();
    }
}