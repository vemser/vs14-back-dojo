package com.example.pessoa.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@Slf4j
public class DataBaseConnection {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    public Connection getConnection() throws Exception {
        Connection connection = DriverManager.getConnection(url, user, password );
        log.info("Conexão bem-sucedida");
        return connection;
    }
}
