package org.acme.javafx.models.enums;

public enum AccountType {
    SAVINGS_ACCOUNT("Savings Account"),
    CURRENT_ACCOUNT("Current Account");

    public String slug;

    AccountType(final String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }
}
