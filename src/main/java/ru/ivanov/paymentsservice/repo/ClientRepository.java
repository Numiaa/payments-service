package ru.ivanov.paymentsservice.repo;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.paymentsservice.model.Client;

import java.util.UUID;

@Repository
public interface ClientRepository extends CrudRepository<Client, UUID> {
    @Transactional(readOnly = true)
    @EntityGraph(attributePaths = "debts")
    Iterable<Client> findAll();
}
