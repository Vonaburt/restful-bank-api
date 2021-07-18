package ru.home.restfulbankapi.funtional_tests;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.home.restfulbankapi.Server;
import ru.home.restfulbankapi.models.Account;
import ru.home.restfulbankapi.models.UserInfo;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Аккаунт")
public class AccountControllerFunctionalTest {

    private final static String HOST = Server.getBASE_URI();

    @Tag("functional")
    @DisplayName("Создание аккаунта. Корректные данные")
    @Test
    void createAccountPositiveTest() {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName("John");
        userInfo.setLastName("Johnson");
        userInfo.setAge((short) 50);

        Account response = Unirest.post(HOST + "account/createAccount")
                .queryString("currency", "EUR")
                .body(userInfo)
                .contentType("application/json")
                .asObject(Account.class).getBody();

        assertEquals(response.getUserInfo(),
                userInfo, "UserInfo is not equals");
    }

    @Tag("functional")
    @DisplayName("Создание аккаунта. Некорректные данные")
    @Test
    void createAccountNegativeTest() {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName("");
        userInfo.setLastName("");
        userInfo.setAge(null);

        HttpResponse<JsonNode> response = Unirest.post(HOST + "account/createAccount")
                .queryString("currency", "EUR")
                .body(userInfo)
                .contentType("application/json")
                .asJson();

        assertEquals(response.getStatusText(),
                "Bad Request", "Request must returns Bad Request");
    }
}
