package org.acme.javafx.models.entities;

import org.acme.javafx.models.base.Person;
import org.acme.javafx.models.entities.accounts.PhysicalAccount;
import org.acme.javafx.models.enums.DocumentType;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_customer")
public class Customer extends Person {

    @ManyToOne
    @JoinColumn(name = "account_id")
    private PhysicalAccount account;

    public Customer() {
    }

    public Customer(
            final UUID id,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt,
            final String name,
            final String middleName,
            final Instant birthDate,
            final String document,
            final DocumentType documentType,
            final Telephone telephone,
            final PhysicalAccount account
    ) {
        super(id, createdAt, updatedAt, deletedAt, name, middleName, birthDate, document, documentType, telephone);
        this.account = account;
    }

    public PhysicalAccount getAccount() {
        return account;
    }

    public void setAccount(PhysicalAccount account) {
        this.account = account;
    }
}
