package org.acme.javafx.service;

import org.acme.javafx.models.base.Account;

import java.util.UUID;

public interface AccountService {

    void createAccount(Account account);

    Account findById(UUID id);
}
