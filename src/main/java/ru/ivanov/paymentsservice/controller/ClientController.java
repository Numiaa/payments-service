package ru.ivanov.paymentsservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.paymentsservice.dto.ClientDTO;
import ru.ivanov.paymentsservice.dto.DebtDTO;
import ru.ivanov.paymentsservice.dto.PaymentDTO;
import ru.ivanov.paymentsservice.dto.mapper.MapStructMapper;
import ru.ivanov.paymentsservice.service.ClientServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
@Validated
public class ClientController {
    private final ClientServiceImpl clientService;

    @GetMapping(path = "")
    public Iterable<ClientDTO> showAllClients() {
        return clientService.showAllClients();
    }

    @PostMapping(path = "")
    public ClientDTO addClient(@RequestParam("clientName") String name) {
        return MapStructMapper.INSTANCE.clientToClientDTO(clientService.addClient(name));
    }

    @PostMapping(path = "debts")
    public DebtDTO addDebtToClient(@RequestParam("clientId") UUID clientId,
                                   @RequestParam("debt") @Positive(message = "Debt amount must be than 0") BigDecimal value) {
        return MapStructMapper.INSTANCE.debtToDebtDTO(clientService.addDebtToClient(clientId, value));
    }

    @PostMapping(path = "debts/payments")
    public PaymentDTO payToClientDebt(@RequestParam("debtId") UUID debtId,
                                      @RequestParam("pay") @Positive(message = "Payment amount must be than 0") BigDecimal value) {
        return MapStructMapper.INSTANCE.paymentToPaymentDTO(clientService.addPayToClientDebt(debtId, value));
    }
}
