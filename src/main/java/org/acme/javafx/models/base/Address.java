package org.acme.javafx.models.base;

import org.acme.javafx.models.enums.AddressType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;
import java.util.UUID;


public abstract class Address extends BaseEntity {

    public Address() {
    }

    public Address(
            UUID id,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt,
            String street,
            String city,
            String state,
            String country,
            String postalCode,
            String apartmentNumber,
            String buildingName,
            String neighborhood,
            String landmark,
            String phone,
            AddressType addressType
    ) {
        super(id, createdAt, updatedAt, deletedAt);
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.apartmentNumber = apartmentNumber;
        this.buildingName = buildingName;
        this.neighborhood = neighborhood;
        this.landmark = landmark;
        this.phone = phone;
        this.addressType = addressType;
    }

    protected String street;
    protected String city;
    protected String state;
    protected String country;
    protected String postalCode;
    protected String apartmentNumber;
    protected String buildingName;
    protected String neighborhood;
    protected String landmark;
    protected String phone;

    @Enumerated(EnumType.STRING)
    protected AddressType addressType;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }
}
