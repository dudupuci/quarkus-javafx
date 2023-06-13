package org.acme.javafx.service;

import org.acme.javafx.models.base.Account;
import org.acme.javafx.repositories.AccountRepositoryBase;

import java.util.UUID;

public interface AccountRepository extends AccountRepositoryBase {

    void create(Account account);

    Account findById(UUID id);
}
