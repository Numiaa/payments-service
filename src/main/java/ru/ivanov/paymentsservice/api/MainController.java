package ru.ivanov.paymentsservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.paymentsservice.dto.ClientDTO;
import ru.ivanov.paymentsservice.dto.DebtDTO;
import ru.ivanov.paymentsservice.dto.PaymentDTO;
import ru.ivanov.paymentsservice.service.ClientServiceImpl;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class MainController {

    private final ClientServiceImpl clientService;

    @GetMapping(path = "/clients/show-all")
    public Iterable<ClientDTO> showAllClients() {
        return clientService.showAllClients();
    }

    @PostMapping(path = "/clients/add")
    public ClientDTO addClient(@RequestParam("clientName") @Valid String name) {
        return clientService.addClient(name);
    }

    @PostMapping(path = "/clients/add-debt-to-client")
    public DebtDTO addDebtToClient(@RequestParam("clientId") UUID clientId,
                                   @RequestParam("debt") @Valid BigDecimal value) {
        return clientService.addDebtToClient(clientId, value);
    }

    @PostMapping(path = "/clients/pay-to-client-debt")
    public PaymentDTO payToClientDebt(@RequestParam("debtId") UUID debtId,
                                      @RequestParam("pay") @Valid BigDecimal value) {
        return clientService.addPayToClientDebt(debtId, value);
    }
}
