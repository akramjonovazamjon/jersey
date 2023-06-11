package org.example.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public interface BaseRepository {

    String USERNAME = "postgres";
    String PASSWORD = "azamjon1999";

    default Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", USERNAME, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
