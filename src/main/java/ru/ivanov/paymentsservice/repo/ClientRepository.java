package ru.ivanov.paymentsservice.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.paymentsservice.model.Client;

import java.util.UUID;

@Repository
public interface ClientRepository extends CrudRepository<Client, UUID> {

    @Transactional
    @Query("select c from Client c where c.id=?1")
    Client findClientById(UUID uuid);
}
