package org.acme.javafx.models.enums;

public enum OwnerType {
    PHYSICAL_PERSON("Physical Person"),
    JURIDIC_PERSON("Juridic Person");

    public String slug;

    OwnerType(final String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }
}
