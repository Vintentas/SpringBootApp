package ru.dhabits.spring_boot_example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dhabits.spring_boot_example.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
