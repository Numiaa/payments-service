package ru.ivanov.paymentsservice.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.paymentsservice.dto.ClientDTO;
import ru.ivanov.paymentsservice.dto.DebtDTO;
import ru.ivanov.paymentsservice.dto.PaymentDTO;
import ru.ivanov.paymentsservice.dto.mapper.MapStructMapper;
import ru.ivanov.paymentsservice.model.Client;
import ru.ivanov.paymentsservice.model.Debt;
import ru.ivanov.paymentsservice.model.Payment;
import ru.ivanov.paymentsservice.repo.ClientRepository;
import ru.ivanov.paymentsservice.repo.DebtRepository;
import ru.ivanov.paymentsservice.repo.PaymentRepository;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final DebtRepository debtRepository;
    private final PaymentRepository paymentRepository;

    public ClientServiceImpl(ClientRepository clientRepository,
                             DebtRepository debtRepository,
                             PaymentRepository paymentRepository) {
        this.clientRepository = clientRepository;
        this.debtRepository = debtRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public Iterable<ClientDTO> showAllClients() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false).map(client -> {
            ClientDTO clientDTO = MapStructMapper.INSTANCE.clientToClientDTO(client);
            clientDTO.setTotalDebts(client.getDebts().stream()
                    .map(debt -> debt.getValue().subtract(debt.getPayments().stream()
                            .map(Payment::getValue).reduce(BigDecimal.ZERO, BigDecimal::add)))
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
            return clientDTO;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ClientDTO addClient(String name) {
        Client client = new Client();
        client.setName(name);
        clientRepository.save(client);
        return MapStructMapper.INSTANCE.clientToClientDTO(client);
    }

    @Override
    @Transactional
    public DebtDTO addDebtToClient(UUID clientId, BigDecimal value) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new DataRetrievalFailureException("Client with id:" + clientId + " not found"));
        Debt debt = new Debt();
        debt.setValue(value);
        debt.setClient(client);
        client.getDebts().add(debt);
        debtRepository.save(debt);
        return MapStructMapper.INSTANCE.debtToDebtDTO(debt);
    }

    @Override
    @Transactional
    public PaymentDTO addPayToClientDebt(UUID debtId, BigDecimal value) {
        Debt debt = debtRepository.findById(debtId).orElseThrow(() ->
                new DataRetrievalFailureException("Debt with id:" + debtId + " not found"));
        BigDecimal factPayments = debt.getPayments().stream()
                .map(Payment::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (factPayments.add(value).compareTo(debt.getValue()) > 0) {
            throw new DataIntegrityViolationException("Payment amount more than debt." +
                    "Acceptable payment: " + debt.getValue().subtract(factPayments));
        }
        Payment payment = new Payment();
        payment.setDebt(debt);
        payment.setValue(value);
        paymentRepository.save(payment);
        debt.getPayments().add(payment);
        return MapStructMapper.INSTANCE.paymentToPaymentDTO(payment);
    }
}
