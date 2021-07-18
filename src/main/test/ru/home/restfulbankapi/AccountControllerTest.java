package ru.home.restfulbankapi;

import org.junit.jupiter.api.*;
import ru.home.restfulbankapi.models.Account;
import ru.home.restfulbankapi.models.UserInfo;

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

    @Tag("functional")
    @DisplayName("Создание аккаунта. Корректные данные")
    @Test
    void createAccountTest() {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName("Kamilla");
        userInfo.setLastName("Kamillkina");
        userInfo.setAge((short) 24);

        Response createAccountResponse = target.path("account")
                .path("createAccount")
                .queryParam("currency", "EUR")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(userInfo));
        Account actualAccount = createAccountResponse.readEntity(Account.class);

        Assertions.assertEquals(actualAccount.getUserInfo(),
                userInfo, "UserInfo is not equals");
    }

    @AfterAll
    static void stopServer() {
        Server.stopServer();
    }
}