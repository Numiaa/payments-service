package ru.ivanov.paymentsservice.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.*;

@Data
@Entity
@Table(name = "debts")
@EqualsAndHashCode(exclude = {"client", "payments"})
public class Debt {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private UUID id;
    @Column(name = "value")
    private BigDecimal value;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToMany(mappedBy = "debt", fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    Set<Payment> payments = new HashSet<>();
}
