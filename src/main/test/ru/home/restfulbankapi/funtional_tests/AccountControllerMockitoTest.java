package ru.home.restfulbankapi.funtional_tests;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import ru.home.restfulbankapi.controllers.AccountController;
import ru.home.restfulbankapi.models.Account;
import ru.home.restfulbankapi.models.Currency;
import ru.home.restfulbankapi.models.UserInfo;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class AccountControllerMockitoTest {

    @Test
    void mockTest() {
        AccountController accountControllerMock = org.mockito.Mockito.mock(AccountController.class);
        when(accountControllerMock.getAccount(1))
                .thenReturn(Response.ok(new Account(new UserInfo("Test", "Testing", (short) 1),
                        Currency.RUR, new BigDecimal(3000))).build());

        assertEquals(accountControllerMock.getAccount(1).getStatus(),
                200);
    }
}
