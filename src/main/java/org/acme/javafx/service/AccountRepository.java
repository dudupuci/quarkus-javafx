package org.acme.javafx.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.acme.javafx.models.base.Account;
import org.acme.javafx.repositories.AccountRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
@Deprecated
public interface AccountRepository extends AccountRepositoryBase {

    void create(Account account);

    Account findById(UUID id);

    PanacheQuery<Account> findAll();
}
