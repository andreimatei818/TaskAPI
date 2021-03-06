package com.task.ING.repository;

import com.task.ING.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Integer> {
    Optional<User> findUserByUsername(String userName);
}
