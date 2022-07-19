package ru.ivanov.paymentsservice.service;

import ru.ivanov.paymentsservice.dto.ClientDTO;
import ru.ivanov.paymentsservice.model.Client;
import ru.ivanov.paymentsservice.model.Debt;
import ru.ivanov.paymentsservice.model.Payment;

import java.math.BigDecimal;
import java.util.UUID;

public interface ClientService {

    Iterable<ClientDTO> showAllClients();

    Client addClient(String name);

    Debt addDebtToClient(UUID clientId, BigDecimal value);

    Payment addPaymentToDebt(UUID debtId, BigDecimal value);
}
