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
            UUID id,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt,
            String name,
            String middleName,
            Instant birthDate,
            String document,
            DocumentType documentType,
            PhysicalAccount account
    ) {
        super(id, createdAt, updatedAt, deletedAt, name, middleName, birthDate, document, documentType);
        this.account = account;
    }

    public static Customer createInstance() {
        return new Customer(
                UUID.randomUUID(),
                Instant.now(),
                Instant.now(),
                null,
                "Eduardo",
                "Pucinelli",
                Instant.now(),
                "10219341",
                DocumentType.CNH,
                PhysicalAccount.createInstance()
        );
    }

    public PhysicalAccount getAccount() {
        return account;
    }

    public void setAccount(PhysicalAccount account) {
        this.account = account;
    }
}
