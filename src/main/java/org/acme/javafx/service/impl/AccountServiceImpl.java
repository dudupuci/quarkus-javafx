package org.acme.javafx.service.impl;

import org.acme.javafx.models.base.Account;
import org.acme.javafx.service.AccountService;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class AccountServiceImpl implements AccountService {
    @Override
    public void createAccount(Account account) {

    }

    @Override
    public Account findById(UUID id) {
        return null;
    }
}
