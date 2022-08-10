package com.example.CustomerServer.repository;


import com.example.CustomerServer.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {
}
