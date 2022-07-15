package ru.ivanov.paymentsservice.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ivanov.paymentsservice.model.Debt;

import java.util.UUID;

@Repository
public interface DebtRepository extends CrudRepository<Debt, UUID> {

    Debt findDebtById(UUID id);
}
