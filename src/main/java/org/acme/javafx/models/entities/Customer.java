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
            Telephone telephone
    ) {
        super(id, createdAt, updatedAt, deletedAt, name, middleName, birthDate, document, documentType, telephone);
    }

}
