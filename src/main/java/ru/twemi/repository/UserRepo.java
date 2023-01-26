package ru.twemi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.twemi.entity.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
