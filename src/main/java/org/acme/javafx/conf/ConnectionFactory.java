package org.acme.javafx.conf;

import lombok.Value;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ApplicationScoped
public class ConnectionFactory {


    private static String url = "jdbc:postgresql://localhost:5432/quarkus-javafx";


    private static String username = "postgres";

    private static String password = "orp101099";


    public static Connection connect() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("PostgreSQL connected.");

        } catch (SQLException err) {
            throw new RuntimeException("Error trying to connect at database: " + err.getMessage());
        }
        return conn;
    }
}
