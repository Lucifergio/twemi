package ru.twemi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.twemi.entity.Authority;

public interface AuthorityRepo extends JpaRepository<Authority, Long> {
    Authority findByRole(String role);
}
