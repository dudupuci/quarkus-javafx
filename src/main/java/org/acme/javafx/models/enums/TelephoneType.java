package org.acme.javafx.models.enums;

public enum TelephoneType {
    RESIDENTIAL("Residential Telephone"),
    MOBILE("Mobile Telephone");

    public String slug;

    TelephoneType(final String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }
}
