**Методы API:**

- createAccount(userInfo, currency) : создание счета в банке, где userInfo - информация о клиенте,  currency - валюта;
- deleteAccount(accountNumber): удаление счета в банке, где accountNumber - номер счета;


- getAccountByNumber(accountNumber) : получение счета клиента, где accountNumber - информация о клиенте;
- getAccountByInfo(firstName, lastName) : получение счета клиента, где firstName - имя клиента, lastName - фамилия клиента;
- getAccountBalance(accountNumber) : получение баланса счета, где accountNumber - номер счета;


- addAmountToAccount(destinationAccountNumber, amount) : пополнение счета клиента, где destinationAccountNumber - номер счет клиента, amount - сумма пополнения
- transferFromAccount(fromAccountNumber, destinationAccountNumber, amount) : перевод средств с одного счета на другой (в том числе операции оплаты), где fromAccountNumber - номер счета отправителя, destinationAccountNumber - номер счета получателя, amount - сумма перевода 


- getAllOperationsHistory(accountNumber) : получение списка всех операций 