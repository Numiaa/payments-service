CREATE TABLE Clients
(
    id   UUID PRIMARY KEY,
    name VARCHAR(80) NOT NULL
);

CREATE TABLE Debts
(
    id        UUID PRIMARY KEY,
    value     NUMERIC NOT NULL,
    client_id UUID    NOT NULL,
    CONSTRAINT debts_client_fk FOREIGN KEY (client_id) REFERENCES Clients ON DELETE CASCADE,
    CONSTRAINT debts_value_min CHECK (value > 0)
);

CREATE TABLE Payments
(
    id      UUID PRIMARY KEY,
    value   NUMERIC NOT NULL,
    debt_id UUID    NOT NULL,
    CONSTRAINT payments_debt_fk FOREIGN KEY (debt_id) REFERENCES Debts ON DELETE CASCADE,
    CONSTRAINT payments_value_min CHECK (value > 0)
);