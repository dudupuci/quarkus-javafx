package org.acme.javafx.models.entities.accounts;

import org.acme.javafx.models.base.Account;
import org.acme.javafx.models.entities.Customer;
import org.acme.javafx.models.enums.AccountType;
import org.acme.javafx.models.enums.OwnerType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@DiscriminatorValue("physical_account")
public final class PhysicalAccount extends Account {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public PhysicalAccount() {
    }

    public PhysicalAccount(
            UUID id,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt,
            Short agency,
            String accountNumber,
            AccountType accountType,
            OwnerType ownerType,
            BigDecimal availableBalance,
            Customer customer,
            String secretKey
    ) {
        super(id, createdAt, updatedAt, deletedAt, agency, accountNumber, accountType, ownerType, availableBalance, secretKey);
        this.customer = customer;
    }

    @Override
    public Short getAgency() {
        return this.agency;
    }

    @Override
    public void setAgency(Short agency) {
        this.agency = agency;
    }

    @Override
    public String getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public OwnerType getOwnerType() {
        return ownerType;
    }

    @Override
    public void setOwnerType(OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    @Override
    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    @Override
    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    @Override
    public String getSecretKey() {
        return secretKey;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}
