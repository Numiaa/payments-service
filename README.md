# payments-service

Задача: создать REST сервис с возможностью взять/погасить денежный кредит.
Стэк: Spring, hibernate/jpa, postgreSQL, liquibase, junit для модульных тестов, openapi для документирования

ENDPOINTS: localhost:8080/clients
- POST: / принимает clientName(string) - добавляет клиента.
- POST: /debts принимает clientId(UUID) & debt(BigDecimal) - дает клиенту в долг
- POST: /debts/payments принимает debtId(UUID) & pay(BigDecimal) - оплачивает долг клиента
- GET: / - выгружает всех клиентов
![Screenshot 2022-07-19 at 17 49 24](https://user-images.githubusercontent.com/95632773/179743161-ec7e902d-f931-4b99-9a3b-6b2ae5896db9.png)

Запуск .jar файла.
java -jar /path_to_jar_file/payments-service-0.0.1-SNAPSHOT.jar
