package org.acme.javafx.models.base;

import org.acme.javafx.models.entities.Telephone;
import org.acme.javafx.models.entities.accounts.PhysicalAccount;
import org.acme.javafx.models.enums.DocumentType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.util.UUID;


public abstract class Person extends BaseEntity {

    public Person() {
    }

    public Person(
            UUID id,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt,
            String name,
            String middleName,
            Instant birthDate,
            String document,
            DocumentType documentType
    ) {
        super(id, createdAt, updatedAt, deletedAt);
        this.id = id;
        this.name = name;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.document = document;
        this.documentType = documentType;
    }

    protected String name;
    protected String middleName;
    protected Instant birthDate;
    protected String document;
    @Enumerated(EnumType.STRING)
    protected DocumentType documentType;
    @ManyToOne
    @JoinColumn(name = "telephone_id")
    protected Telephone telephone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Telephone getTelephone() {
        return telephone;
    }

    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
    }

}