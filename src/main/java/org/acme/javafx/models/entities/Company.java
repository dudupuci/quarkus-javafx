package org.acme.javafx.models.entities;

import org.acme.javafx.models.base.Address;
import org.acme.javafx.models.base.BaseEntity;
import org.acme.javafx.models.entities.accounts.JuridicAccount;
import org.acme.javafx.models.entities.adresses.CommercialAddress;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_company")
public class Company extends BaseEntity {


    public Company() {
    }

    public Company(
            final UUID id,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt,
            final String name,
            final CommercialAddress address,
            final String phone,
            final String email,
            final String website,
            final Instant foundationDate,
            final Double revenue,
            final String sector,
            final String description,
            final JuridicAccount account
    ) {
        super(id, createdAt, updatedAt, deletedAt);
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.foundationDate = foundationDate;
        this.revenue = revenue;
        this.sector = sector;
        this.description = description;
        this.account = account;
    }

    private String name;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private CommercialAddress address;
    private String phone;
    private String email;
    private String website;
    private Instant foundationDate;
    private double revenue;
    private String sector;
    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private JuridicAccount account;

    public JuridicAccount getAccount() {
        return account;
    }

    public void setAccount(JuridicAccount account) {
        this.account = account;
    }
    //private byte[] logo;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommercialAddress getAddress() {
        return address;
    }

    public void setAddress(CommercialAddress address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Instant getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(Instant foundationDate) {
        this.foundationDate = foundationDate;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}