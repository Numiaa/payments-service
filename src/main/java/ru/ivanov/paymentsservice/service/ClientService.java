package ru.ivanov.paymentsservice.service;

import ru.ivanov.paymentsservice.dto.ClientDTO;
import ru.ivanov.paymentsservice.dto.DebtDTO;
import ru.ivanov.paymentsservice.dto.PaymentDTO;
import java.math.BigDecimal;
import java.util.UUID;

public interface ClientService {

    Iterable<ClientDTO> showAllClients();

    ClientDTO addClient(String name);

    DebtDTO addDebtToClient(UUID clientId, BigDecimal value);

    PaymentDTO addPayToClientDebt(UUID debtId, BigDecimal value);
}
