package org.acme.javafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.acme.javafx.interfaces.SecretKeyGen;
import org.acme.javafx.models.entities.Customer;
import org.acme.javafx.models.entities.Telephone;
import org.acme.javafx.models.entities.accounts.PhysicalAccount;
import org.acme.javafx.models.enums.AccountType;
import org.acme.javafx.models.enums.DocumentType;
import org.acme.javafx.models.enums.OwnerType;
import org.acme.javafx.models.enums.TelephoneType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ResourceBundle;
import java.util.UUID;

@ApplicationScoped
public class LoginViewController implements Initializable {

    @Inject
    DataSource dataSource;

    @FXML
    private TextField identifierTextField;

    @FXML
    private TextField secretKeyTextField;

    @FXML
    private Button accessButton;

    @FXML
    private Label statusLabel;

    @FXML
    protected void onAccessButtonAction() {
        getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private void getInstance() {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);

            String insertAccountSql = "INSERT INTO account " +
                    "(type, id, created_at, deleted_at, updated_at, account_number, account_type, agency, available_balance, owner_type, secret_key, company_id, customer_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            String insertCustomerSql = "INSERT INTO tb_customer " +
                    "(id, created_at, deleted_at, updated_at, birth_date, document, document_type, middle_name, name, telephone_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            String insertTelephoneSql = "INSERT INTO tb_telephone " +
                    "(id, created_at, deleted_at, updated_at, number, telephone_type) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            var customerTelephone = new Telephone(
                    UUID.randomUUID(),
                    Instant.now(),
                    Instant.now(),
                    null,
                    "+55 4499954-3420",
                    TelephoneType.MOBILE
            );

            var customer = new Customer(
                    UUID.randomUUID(),
                    Instant.now(),
                    Instant.now(),
                    null,
                    "Eduardo",
                    "Pucinelli",
                    Instant.from(LocalDateTime.of(2000, 07, 28, 10, 10).toInstant(ZoneOffset.UTC)),
                    "139107152",
                    DocumentType.RG,
                    customerTelephone
            );

            var account = new PhysicalAccount(
                    UUID.randomUUID(),
                    Instant.now(),
                    Instant.now(),
                    null,
                    Short.valueOf("0001"),
                    "10913414",
                    AccountType.CURRENT_ACCOUNT,
                    OwnerType.PHYSICAL_PERSON,
                    BigDecimal.valueOf(28000),
                    customer,
                    SecretKeyGen.generateSecretKey()
            );

            try (PreparedStatement insertAccountStatement = connection.prepareStatement(insertAccountSql, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement insertCustomerStatement = connection.prepareStatement(insertCustomerSql);
                 PreparedStatement insertTelephoneStatement = connection.prepareStatement(insertTelephoneSql)) {

                insertTelephoneStatement.setObject(1, customerTelephone.getId());
                insertTelephoneStatement.setObject(2, Timestamp.from(customerTelephone.getCreatedAt()));
                insertTelephoneStatement.setObject(3, null);
                insertTelephoneStatement.setObject(4, Timestamp.from(customerTelephone.getUpdatedAt()));
                insertTelephoneStatement.setString(5, customerTelephone.getNumber());
                insertTelephoneStatement.setObject(6, customerTelephone.getTelephoneType().toString());
                insertTelephoneStatement.executeUpdate();

                insertCustomerStatement.setObject(1, customer.getId());
                insertCustomerStatement.setTimestamp(2, Timestamp.from(customer.getCreatedAt()));
                insertCustomerStatement.setTimestamp(3, null);
                insertCustomerStatement.setTimestamp(4, Timestamp.from(customer.getUpdatedAt()));
                insertCustomerStatement.setTimestamp(5, Timestamp.from(customer.getBirthDate()));
                insertCustomerStatement.setString(6, customer.getDocument());
                insertCustomerStatement.setString(7, customer.getDocumentType().toString());
                insertCustomerStatement.setString(8, customer.getMiddleName());
                insertCustomerStatement.setString(9, customer.getName());
                insertCustomerStatement.setObject(10, customer.getTelephone().getId());
                insertCustomerStatement.executeUpdate();

                insertAccountStatement.setString(1, "physical_account");
                insertAccountStatement.setObject(2, account.getId());
                insertAccountStatement.setTimestamp(3, Timestamp.from(account.getCreatedAt()));
                insertAccountStatement.setTimestamp(4, null);
                insertAccountStatement.setTimestamp(5, Timestamp.from(account.getUpdatedAt()));
                insertAccountStatement.setString(6, account.getAccountNumber());
                insertAccountStatement.setString(7, account.getAccountType().toString());
                insertAccountStatement.setObject(8, account.getAgency());
                insertAccountStatement.setObject(9, account.getAvailableBalance());
                insertAccountStatement.setString(10, account.getOwnerType().toString());
                insertAccountStatement.setString(11, account.getSecretKey());
                insertAccountStatement.setObject(12, null);
                insertAccountStatement.setObject(13, account.getCustomer().getId());
                insertAccountStatement.executeUpdate();


                connection.commit();
            } catch (SQLException err) {
                connection.rollback();
                throw new RuntimeException("Error trying to insert: " + err.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (
                SQLException err) {
            throw new RuntimeException("Error trying to establish database connection: " + err.getMessage());
        }
    }


}
