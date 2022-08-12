package com.example.CustomerServer.repository;


import com.example.CustomerServer.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {
}
