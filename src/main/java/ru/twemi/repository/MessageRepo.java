package ru.twemi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.twemi.entity.Message;

public interface MessageRepo extends JpaRepository<Message, Long > {
}
