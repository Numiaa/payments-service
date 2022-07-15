# payments-service

Задача: создать REST сервис с возможностью взять/погасить денежный кредит.
Стэк: Spring, hibernate/jpa, postgreSQL, liquibase, junit для модульных тестов, openapi для документирования

Точка: localhost:8080/api
- POST: /clients/add принимает clientName(string) 
- POST: /clients/add-debt-to-client принимает clientId(UUID) & debt(BigDecimal)
- POST: /clients/pay-to-client-debt принимает debtId(UUID) & pay(BigDecimal)
- GET: /clients/show-all - выгружает всех клиентов
![Screenshot 2022-07-15 at 18 39 40](https://user-images.githubusercontent.com/95632773/179226104-fb22a1fe-a2ea-4548-a4ed-63fb8cafdd1c.png)



