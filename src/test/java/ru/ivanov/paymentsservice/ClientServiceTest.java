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
import ru.ivanov.paymentsservice.service.ClientService;
import ru.ivanov.paymentsservice.service.ClientServiceImpl;

import java.math.BigDecimal;
import java.util.UUID;

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
    }

    @Test
    public void addDebtToClientTest() {

    }

    @Test
    public void addPayToClientDebt() {
    }
}
