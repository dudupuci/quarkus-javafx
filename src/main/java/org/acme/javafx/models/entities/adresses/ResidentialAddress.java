package org.acme.javafx.models.entities.adresses;

import org.acme.javafx.models.base.Address;
import org.acme.javafx.models.enums.AddressType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@DiscriminatorValue("residential_address")
public class ResidentialAddress extends Address {

    public ResidentialAddress() {
    }

    public ResidentialAddress(
            final UUID id,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt,
            final String street,
            final String city,
            final String state,
            final String country,
            final String postalCode,
            final String apartmentNumber,
            final String buildingName,
            final String neighborhood,
            final String landmark,
            final String phone,
            final AddressType addressType
    ) {
        super(id, createdAt, updatedAt, deletedAt, street, city, state, country, postalCode, apartmentNumber, buildingName, neighborhood, landmark, phone, addressType);
    }

}