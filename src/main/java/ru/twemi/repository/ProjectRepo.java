package ru.twemi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.twemi.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {
    Project findByTitleAndDescription(String title, String description);
}
