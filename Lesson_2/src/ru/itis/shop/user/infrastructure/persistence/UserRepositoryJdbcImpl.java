package ru.itis.shop.user.infrastructure.persistence;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/shop_db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "Gleb526173";


    @Override
    public void save(User user) {
        return;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public void update(User user) {
        return;


    }

    
    @Override
    public List<User> findAll() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            // select * from product
            List<User> users = new java.util.ArrayList<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from Users")) {
                    while (resultSet.next()) {
                        String userStr = (resultSet.getInt("id") + "|" +
                                resultSet.getString("name") +
                                "|" + resultSet.getString("email") +
                                "|" + resultSet.getString("password") +
                                "|" + resultSet.getString("profile_description"));
                        UserMapper uMapper = new UserMapper();
                        User user = uMapper.fromLine(userStr);
                        users.add(user);
                    }
                }
            }

            return users;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
                }
    }
}
