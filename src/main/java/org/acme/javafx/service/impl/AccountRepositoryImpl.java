package org.acme.javafx.service.impl;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.acme.javafx.models.base.Account;
import org.acme.javafx.repositories.AccountRepositoryBase;
import org.acme.javafx.service.AccountRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Deprecated
public class AccountRepositoryImpl implements AccountRepository {

    private AccountRepositoryBase repository;

    @Inject
    public AccountRepositoryImpl(AccountRepositoryBase repository) {
        this.repository = repository;
    }

    @Override
    public Account findByIdAndSecretKey(UUID id, String secretKey) {
        return this.repository.findByIdAndSecretKey(id, secretKey);
    }

    @Override
    public void create(Account account) {
        try {
            this.repository.persist(account);
        } catch (Exception err) {
            throw new RuntimeException("Error trying to save account: "+err.getMessage());
        }
    }

    @Override
    public Account findById(UUID id) {
        return this.repository.findById(id);
    }

    @Override
    public PanacheQuery<Account> findAll() {
        return this.repository.findAll();
    }
}
