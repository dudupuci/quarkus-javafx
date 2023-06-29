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
@DiscriminatorValue("commercial_address")
public class CommercialAddress extends Address {

    public CommercialAddress() {
    }

    public CommercialAddress(
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
            final AddressType addressType,
            final LocalTime openingTime,
            final LocalTime closureTime,
            final Boolean openOnSaturday
    ) {
        super(id, createdAt, updatedAt, deletedAt, street, city, state, country, postalCode, apartmentNumber, buildingName, neighborhood, landmark, phone, addressType);
        this.openingTime = openingTime;
        this.closureTime = closureTime;
        this.openOnSaturday = openOnSaturday;
    }

    private LocalTime openingTime;
    private LocalTime closureTime;
    private Boolean openOnSaturday;

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosureTime() {
        return closureTime;
    }

    public void setClosureTime(LocalTime closureTime) {
        this.closureTime = closureTime;
    }

    public Boolean getOpenOnSaturday() {
        return openOnSaturday;
    }

    public void setOpenOnSaturday(Boolean openOnSaturday) {
        this.openOnSaturday = openOnSaturday;
    }
}
