package ru.itis.user.repository;

import ru.itis.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Integer id);

    List<User> findAll();

    void updateByEmail(String email, String newDescription);

    List<User> findByProfileDescription(String description);
}
