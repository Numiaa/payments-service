package ru.ivanov.paymentsservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ivanov.paymentsservice.dto.ClientDTO;
import ru.ivanov.paymentsservice.dto.DebtDTO;
import ru.ivanov.paymentsservice.dto.PaymentDTO;
import ru.ivanov.paymentsservice.repo.ClientRepository;
import ru.ivanov.paymentsservice.repo.DebtRepository;
import ru.ivanov.paymentsservice.repo.PaymentRepository;
import ru.ivanov.paymentsservice.service.ClientServiceImpl;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientServiceImpl clientService;

    @Test
    public void addClientTest() {
        ClientDTO clientDTO = clientService.addClient("TestName");
        Assertions.assertNotNull(clientDTO);
        Assertions.assertEquals("TestName", clientDTO.getName());
    }

    @Test
    public void addDebtToClientTest() {
        ClientDTO clientDTO = clientService.addClient("testDebtToClient");
        UUID clientId = clientDTO.getId();
        BigDecimal value = BigDecimal.valueOf(500.00);
        DebtDTO debtDTO = clientService.addDebtToClient(clientId, value);
        Assertions.assertNotNull(debtDTO);
        Assertions.assertEquals(clientDTO.getId(), debtDTO.getClientId());
    }

    @Test
    public void addPayToClientDebt() {
        ClientDTO clientDTO = clientService.addClient("testPayToClientDebt");
        UUID clientId = clientDTO.getId();
        BigDecimal value = BigDecimal.valueOf(500.00);
        DebtDTO debtDTO = clientService.addDebtToClient(clientId, value);
        UUID debtId = debtDTO.getId();
        PaymentDTO paymentDTO = clientService.addPayToClientDebt(debtId, value);
        Assertions.assertNotNull(paymentDTO);
        Assertions.assertEquals(debtDTO.getId(), paymentDTO.getDebtId());
    }
}
