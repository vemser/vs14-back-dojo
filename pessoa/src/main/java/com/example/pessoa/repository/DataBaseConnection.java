package com.example.pessoa.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@Slf4j
public class DataBaseConnection {

    private Connection con;
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public Connection startConnection() throws Exception {
        this.con = DriverManager.getConnection(url, user, password);
        //log.info("Conexão bem-sucedida");
        return con;
    }

    public Connection getConnection() {
        return this.con;
    }
}
