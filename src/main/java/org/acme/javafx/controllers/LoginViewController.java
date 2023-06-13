package org.acme.javafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.acme.javafx.models.entities.accounts.PhysicalAccount;
import org.acme.javafx.service.impl.AccountRepositoryImpl;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;

@Singleton
public class LoginViewController implements Initializable {

    @Inject
    private AccountRepositoryImpl service;

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
        var account = PhysicalAccount.createInstance();
        this.service.create(account);
        System.out.println("CRIADO");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
