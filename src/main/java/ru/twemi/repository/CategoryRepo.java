package ru.twemi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.twemi.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
