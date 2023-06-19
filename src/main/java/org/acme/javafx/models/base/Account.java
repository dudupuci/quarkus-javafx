package org.acme.javafx.models.base;

import org.acme.javafx.interfaces.SecretKeyGen;
import org.acme.javafx.models.enums.AccountType;
import org.acme.javafx.models.enums.OwnerType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_account")
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

    @Column(name = "agency")
    protected Short agency;
    @Column(name = "account_number", nullable = false)
    protected String accountNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    protected AccountType accountType;
    @Enumerated(EnumType.STRING)
    @Column(name = "owner_type", nullable = false)
    protected OwnerType ownerType;
    @Column(name = "available_balance", nullable = false)
    protected BigDecimal availableBalance;
    @Column(name = "secret_key", nullable = false)
    protected String secretKey;

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

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @PrePersist
    public void prePersistSecretKey() {
        this.secretKey = SecretKeyGen.generateSecretKey();
    }
}
