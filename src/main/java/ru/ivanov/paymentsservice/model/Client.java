package ru.ivanov.paymentsservice.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@Entity
@Table(name = "clients")
@EqualsAndHashCode(exclude = {"debts"})
@Getter
@Setter
public class Client {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private UUID id;
    @Column(name = "name")
    @Length(min = 1, max = 128)
    private String name;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    Set<Debt> debts = new LinkedHashSet<>();
}
