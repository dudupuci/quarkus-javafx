package org.acme.javafx.repositories;


import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.acme.javafx.models.base.Account;

import java.util.UUID;

@Deprecated
public interface AccountRepositoryBase extends PanacheRepositoryBase<Account, UUID> {
    Account findByIdAndSecretKey(UUID id, String secretKey);
}
