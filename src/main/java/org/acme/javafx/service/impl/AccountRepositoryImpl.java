package org.acme.javafx.service.impl;

import org.acme.javafx.models.base.Account;
import org.acme.javafx.repositories.AccountRepositoryBase;
import org.acme.javafx.service.AccountRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class AccountRepositoryImpl implements AccountRepository {

    @Inject
    private AccountRepositoryBase repository;

    @Override
    public Account findByIdAndSecretKey(UUID id, String secretKey) {
        return this.repository.findByIdAndSecretKey(id, secretKey);
    }

    @Override
    public void create(Account account) {
        this.repository.persist(account);
    }

    @Override
    public Account findById(UUID id) {
        return this.repository.findById(id);
    }
}
