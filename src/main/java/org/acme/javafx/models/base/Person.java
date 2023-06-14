package org.acme.javafx.models.base;

import org.acme.javafx.models.entities.Telephone;
import org.acme.javafx.models.enums.DocumentType;

import javax.persistence.*;
import javax.xml.stream.events.StartElement;
import java.time.Instant;
import java.util.UUID;


@MappedSuperclass
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
            DocumentType documentType,
            Telephone telephone
    ) {
        super(id, createdAt, updatedAt, deletedAt);
        this.id = id;
        this.name = name;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.document = document;
        this.documentType = documentType;
        this.telephone = telephone;
    }

    protected String name;
    @Column(name = "middle_name")
    protected String middleName;
    @Column(name = "birth_date")
    protected Instant birthDate;
    protected String document;
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
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