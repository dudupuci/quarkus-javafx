package org.acme.javafx.models.entities;

import org.acme.javafx.models.base.BaseEntity;
import org.acme.javafx.models.enums.TelephoneType;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_telephone")
public class Telephone extends BaseEntity {

    public Telephone() {
    }

    public Telephone(UUID id, Instant createdAt, Instant updatedAt, Instant deletedAt, String number, TelephoneType telephoneType) {
        super(id, createdAt, updatedAt, deletedAt);
        this.number = number;
        this.telephoneType = telephoneType;
    }

    protected String number;
    protected TelephoneType telephoneType;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public TelephoneType getTelephoneType() {
        return telephoneType;
    }

    public void setTelephoneType(TelephoneType telephoneType) {
        this.telephoneType = telephoneType;
    }
}
