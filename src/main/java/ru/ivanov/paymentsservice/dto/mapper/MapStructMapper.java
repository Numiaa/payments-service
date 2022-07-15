package ru.ivanov.paymentsservice.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.ivanov.paymentsservice.dto.ClientDTO;
import ru.ivanov.paymentsservice.dto.DebtDTO;
import ru.ivanov.paymentsservice.dto.PaymentDTO;
import ru.ivanov.paymentsservice.model.Client;
import ru.ivanov.paymentsservice.model.Debt;
import ru.ivanov.paymentsservice.model.Payment;

@Mapper
public interface MapStructMapper {

    MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

    @Mapping(source = "name", target = "name")
    ClientDTO clientToClientDTO(Client client);
    @Mapping(source = "client.id", target = "clientId")
    DebtDTO debtToDebtDTO(Debt debt);
    @Mapping(source = "debt.id", target = "debtId")
    PaymentDTO paymentToPaymentDTO(Payment payment);
}
