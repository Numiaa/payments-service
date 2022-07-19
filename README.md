# payments-service

Задача: создать REST сервис с возможностью взять/погасить денежный кредит.
Стэк: Spring, hibernate/jpa, postgreSQL, liquibase, junit для модульных тестов, openapi для документирования

ENDPOINTS: localhost:8080/api
- POST: / принимает clientName(string) - добавляет клиента.
- POST: /debts принимает clientId(UUID) & debt(BigDecimal) - дает клиенту в долг
- POST: /debts/payments принимает debtId(UUID) & pay(BigDecimal) - оплачивает долг клиента
- GET: / - выгружает всех клиентов



