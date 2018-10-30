**Методы API:**

- createAccount(userInfo, currency) : создание счета в банке, где userInfo - информация о клиенте,  currency - валюта;
- deleteAccount(accountNumber): удаление счета в банке, где accountNumber - номер счета;


- getAccountByNumber(accountNumber) : получение счета клиента, где accountNumber - информация о клиенте;
- getAccountByInfo(firstName, lastName) : получение счета клиента, где firstName - имя клиента, lastName - фамилия клиента;
- getAccountBalance(accountNumber) : получение баланса счета, где accountNumber - номер счета;


- transferFromAccount(fromAccountNumber, destinationAccountNumber, amount) : перед средств с одного счета на другой (в том числе операции оплаты) 


- getAllOperationsHistory(accountNumber) : получение списка всех операций 