package com.example.CustomerServer.service;


import com.example.CustomerServer.dto.LoginDTO;
import com.example.CustomerServer.dto.ResponseDTO;
import com.example.CustomerServer.dto.UserDTO;
import com.example.CustomerServer.model.UserData;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IUserInterface {
    ResponseEntity<ResponseDTO> createUserProfile(UserDTO userDTO);

    List<UserData> getUserList();

    ResponseDTO loginValidation(LoginDTO loginDTO);

    void deleteUserData(int userId);

    ResponseEntity<ResponseDTO> updateUserById(int userId, UserDTO userDTO);

    Optional<UserData> getUserById(int userId);

    ResponseEntity<ResponseDTO> getUserByToken(String token);
}
