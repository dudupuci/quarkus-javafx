package org.acme.javafx.models.enums;

public enum AddressType {
    RESIDENTIAL("Residential Address"),
    NO_RESIDENTIAL("No Residential Address");

    public String slug;

    AddressType(final String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }
}
