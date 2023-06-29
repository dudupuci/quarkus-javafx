package org.acme.javafx.controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.acme.javafx.interfaces.SecretKeyGen;
import org.acme.javafx.models.entities.Customer;
import org.acme.javafx.models.entities.Telephone;
import org.acme.javafx.models.entities.accounts.PhysicalAccount;
import org.acme.javafx.models.enums.AccountType;
import org.acme.javafx.models.enums.DocumentType;
import org.acme.javafx.models.enums.OwnerType;
import org.acme.javafx.models.enums.TelephoneType;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.logging.Logger;

@Singleton
public class LoginViewController implements Initializable {
    private static final Duration PAGE_TRANSITION_DURATION = Duration.seconds(2);
    private static final String AUTHENTICATION_SUCCESS = "Authentication Success!";
    private static final String AUTHENTICATION_FAILED = "Authentication Failed!";
    private static final Logger logger = Logger.getLogger(LoginViewController.class.getName());

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
    private Hyperlink registerHyperlink;

    @FXML
    protected void onAccessButtonAction() {
        getInstance();
        /*try {
            String identifier = identifierTextField.getText();
            String secretKey = secretKeyTextField.getText();
            var isAuthenticated = getAccountCredentials(UUID.fromString(identifier), secretKey);
            logger.info("Authentication status: " + isAuthenticated);

            if (isAuthenticated) {
                statusLabel.setText(AUTHENTICATION_SUCCESS);
                SystemAlert.showAlertWithDelay(AUTHENTICATION_SUCCESS, null, "Enjoy the system.", Alert.AlertType.INFORMATION);
                var pauseTransition = new PauseTransition(PAGE_TRANSITION_DURATION);
                pauseTransition.setOnFinished(event -> {

                    Stage currentStage = (Stage) accessButton.getScene().getWindow();
                    currentStage.close();

                    loadView("/gui/HomeView.fxml", initializer -> {
                    });
                });
                pauseTransition.play();
            } else {
                statusLabel.setText(AUTHENTICATION_FAILED);
            }
        } catch (Exception err) {
            SystemAlert.showAlertWithDelay(err.getClass().getName(), null, err.getMessage(), Alert.AlertType.ERROR);
        }

         */
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private boolean getAccountCredentials(UUID id, String secretKey) {
        ResultSet resultSet;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM tb_account " +
                    "WHERE id = ? AND secret_key = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setObject(1, id);
            preparedStatement.setString(2, secretKey);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                var accountId = resultSet.getObject("id");
                var accountSecretKey = resultSet.getString("secret_key");

                if (!resultSet.next() && accountId.equals(id) && accountSecretKey.equals(secretKey)) {
                    return true;
                }
            }

        } catch (SQLException err) {
            throw new RuntimeException("Error: " + err.getMessage());
        }
        return false;
    }

    private void getInstance() {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);

            String insertAccountSql = "INSERT INTO tb_account " +
                    "(type, id, created_at, deleted_at, updated_at, account_number, account_type, agency, available_balance, owner_type, secret_key, company_id, customer_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            String insertCustomerSql = "INSERT INTO tb_customer " +
                    "(id, created_at, deleted_at, updated_at, birth_date, document, document_type, middle_name, name, telephone_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            String insertTelephoneSql = "INSERT INTO tb_telephone " +
                    "(id, created_at, deleted_at, updated_at, number, telephone_type) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            String updateCustomerSql = "UPDATE tb_customer SET account_id = ? WHERE id = ?";

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
                    customerTelephone,
                    null
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

            customer.setAccount(account);

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

                try (PreparedStatement alterCustomerTable = connection.prepareStatement(updateCustomerSql)) {
                    alterCustomerTable.setObject(1, customer.getAccount().getId());
                    alterCustomerTable.setObject(2, customer.getId());
                    alterCustomerTable.executeUpdate();
                }


                connection.commit();
            } catch (SQLException err) {
                connection.rollback();
                throw new RuntimeException("Error trying to insert: " + err.getMessage());
            } finally {
                System.out.println(customer.getAccount());
                connection.setAutoCommit(true);
            }
        } catch (
                SQLException err) {
            throw new RuntimeException("Error trying to establish database connection: " + err.getMessage());
        }
    }

    private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            AnchorPane parent = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent, 600, 600));

            stage.initStyle(StageStyle.UNDECORATED);

            T controller = loader.getController();
            initializingAction.accept(controller);

            stage.show();
        } catch (IOException err) {
            throw new RuntimeException("" + err.getMessage());
        }
    }

    @FXML
    protected void onRegisterHyperlinkAction() {
        var pauseTransition = new PauseTransition(PAGE_TRANSITION_DURATION);
        pauseTransition.setOnFinished(event -> {

            Stage currentStage = (Stage) registerHyperlink.getScene().getWindow();
            currentStage.close();

            loadView("/gui/RegisterView.fxml", initializer -> {
            });
        });
        pauseTransition.play();
    }

}
