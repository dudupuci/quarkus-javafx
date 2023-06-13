package org.acme.javafx;

import org.acme.javafx.conf.StartupScene;
import java.io.IOException;
import java.net.URL;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import lombok.SneakyThrows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App {

	@Inject
	FXMLLoader fxmlLoader;

	@SneakyThrows
	public void start(@Observes @StartupScene Stage stage) {

		try {
			URL fxml = getClass().getResource("/gui/MainView.fxml");
			Parent fxmlParent = fxmlLoader.load(fxml.openStream());
			stage.setScene(new Scene(fxmlParent, 600, 600));
			stage.setTitle("Hello World Quarkus and JavaFX!");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
