package ru.ivanov.paymentsservice.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "payments")
@EqualsAndHashCode(exclude = {"debt"})
public class Payment {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private UUID id;
    @Column(name = "value")
    private BigDecimal value;
    @ManyToOne
    @JoinColumn(name = "debt_id")
    private Debt debt;
}
