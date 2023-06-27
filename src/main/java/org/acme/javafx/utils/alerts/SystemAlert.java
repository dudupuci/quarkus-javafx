package org.acme.javafx.utils.alerts;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.util.Duration;

public class SystemAlert {
    private static final javafx.util.Duration ALERT_DURATION = Duration.seconds(1);

    public static void showAlertWithDelay(final String title, final String header, final String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        var pauseTransition = new PauseTransition(ALERT_DURATION);
        pauseTransition.setOnFinished(event -> alert.show());
        pauseTransition.play();
    }
}
