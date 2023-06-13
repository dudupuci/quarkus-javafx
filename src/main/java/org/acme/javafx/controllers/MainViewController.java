package org.acme.javafx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.acme.javafx.models.entities.GitVirtualRepository;
import org.acme.javafx.service.impl.GitHubServiceImpl;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class MainViewController {

    @FXML
    Button showButton;

    @FXML
    TextField usernameTextField;

    @Inject
    GitHubServiceImpl gitHubService;

    @FXML
    protected void onShowButtonAction() {
        List<GitVirtualRepository> repositories = this.gitHubService.getAllRepositories(usernameTextField.getText());
        System.out.println(usernameTextField.getText());
        for (GitVirtualRepository repository : repositories) {
            System.out.println(repository);
        }
    }
}
