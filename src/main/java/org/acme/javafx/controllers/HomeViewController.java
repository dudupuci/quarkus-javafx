package org.acme.javafx.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

import javax.inject.Singleton;

@Singleton
public class HomeViewController {

    @FXML
    private MenuItem systemExitMenuItem;

    @FXML
    protected void onSystemExitMenuItemAction() {
        Platform.exit();
    }
}
