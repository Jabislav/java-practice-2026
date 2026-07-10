package ru.itis.app;

import ru.itis.jdbc.DriverManagerDataSource;
import ru.itis.user.api.UserConsoleOperations;
import ru.itis.user.application.UserService;
import ru.itis.user.repository.UserRepository;
import ru.itis.user.infrastructure.persistence.jdbc.UserRepositoryJdbcImpl;
import ru.itis.util.PropertiesReader;

import javax.sql.DataSource;
import java.util.Properties;

public class MainShop {
    public static void main(String[] args) {
        Properties properties = new PropertiesReader("application.properties").loadProperties();

        DataSource dataSource = new DriverManagerDataSource(
                properties.getProperty("db.url"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password"));

        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);
        UserService userService = new UserService(userRepository);
        UserConsoleOperations operations = new UserConsoleOperations(userService);

        while (true) {
            operations.showMenu();
        }
    }
}
