package com.example.CustomerServer.repository;


import com.example.CustomerServer.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserData, Integer> {
    public Optional<UserData> findByEmailId(String emailId);
}
