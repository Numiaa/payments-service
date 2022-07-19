package ru.ivanov.paymentsservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ivanov.paymentsservice.model.Client;
import ru.ivanov.paymentsservice.model.Debt;
import ru.ivanov.paymentsservice.model.Payment;
import ru.ivanov.paymentsservice.repo.ClientRepository;
import ru.ivanov.paymentsservice.repo.DebtRepository;
import ru.ivanov.paymentsservice.repo.PaymentRepository;
import ru.ivanov.paymentsservice.service.ClientService;

import java.math.BigDecimal;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DebtRepository debtRepository;
    @Autowired
    private PaymentRepository paymentRepository;


    @Test
    public void addClientTest() {
        Client c = clientService.addClient("Batman");
        Assertions.assertEquals("Batman", clientRepository.findById(c.getId()).get().getName());
    }

    @Test
    public void addDebtToClientTest() {
        Client c = clientService.addClient("Batman");
        Debt d = clientService.addDebtToClient(c.getId(), BigDecimal.ONE);
        Assertions.assertEquals(BigDecimal.ONE, debtRepository.findById(d.getId()).get().getValue());

    }

    @Test
    public void addPayToClientDebt() {
        Client c = clientService.addClient("Batman");
        Debt d = clientService.addDebtToClient(c.getId(), BigDecimal.TEN);
        Payment p = clientService.addPaymentToDebt(d.getId(), BigDecimal.ONE);
        Assertions.assertEquals(BigDecimal.ONE,paymentRepository.findById(p.getId()).get().getValue());
    }
}
