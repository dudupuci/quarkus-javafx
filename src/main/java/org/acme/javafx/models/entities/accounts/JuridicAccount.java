package org.acme.javafx.models.entities.accounts;

import org.acme.javafx.models.base.Account;
import org.acme.javafx.models.entities.Company;
import org.acme.javafx.models.enums.AccountType;
import org.acme.javafx.models.enums.OwnerType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;


@Entity
@DiscriminatorValue("juridic_account")
public final class JuridicAccount extends Account {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public JuridicAccount(
            UUID id,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt,
            Short agency,
            String accountNumber,
            AccountType accountType,
            OwnerType ownerType,
            BigDecimal availableBalance,
            Company company,
            String secretKey
    ) {
        super(id, createdAt, updatedAt, deletedAt, agency, accountNumber, accountType, ownerType, availableBalance, secretKey);
        this.company = company;
    }

    public JuridicAccount() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

}
