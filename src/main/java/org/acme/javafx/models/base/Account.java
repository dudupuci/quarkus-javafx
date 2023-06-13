package org.acme.javafx.models.base;

import org.acme.javafx.interfaces.SecretKeyGen;
import org.acme.javafx.models.enums.AccountType;
import org.acme.javafx.models.enums.OwnerType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Account extends BaseEntity {

    public Account() {
    }

    public Account(
            UUID id,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt,
            Short agency,
            String accountNumber,
            AccountType accountType,
            OwnerType ownerType,
            BigDecimal availableBalance,
            String secretKey
    ) {
        super(id, createdAt, updatedAt, deletedAt);
        this.agency = agency;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.ownerType = ownerType;
        this.availableBalance = availableBalance;
        this.secretKey = secretKey;
    }

    protected Short agency;
    protected String accountNumber;
    @Enumerated(EnumType.STRING)
    protected AccountType accountType;
    @Enumerated(EnumType.STRING)
    protected OwnerType ownerType;
    protected BigDecimal availableBalance;
    protected String secretKey = SecretKeyGen.generateSecretKey();

    public abstract Short getAgency();

    public abstract void setAgency(Short agency);

    public abstract String getAccountNumber();

    public abstract void setAccountNumber(String accountNumber);

    public abstract AccountType getAccountType();

    public abstract void setAccountType(AccountType accountType);

    public abstract OwnerType getOwnerType();

    public abstract void setOwnerType(OwnerType ownerType);

    public abstract BigDecimal getAvailableBalance();

    public abstract void setAvailableBalance(BigDecimal availableBalance);

    public abstract String getSecretKey();
}
