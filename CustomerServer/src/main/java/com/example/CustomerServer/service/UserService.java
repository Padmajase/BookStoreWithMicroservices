package com.example.CustomerServer.service;

import com.example.CustomerServer.dto.LoginDTO;
import com.example.CustomerServer.dto.ResponseDTO;
import com.example.CustomerServer.dto.UserDTO;
import com.example.CustomerServer.exception.UserRegistrationException;
import com.example.CustomerServer.model.UserData;
import com.example.CustomerServer.repository.UserRepository;
import com.example.CustomerServer.token.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserInterface{

    /*************** injecting User repository object here ***************/
    @Autowired
    private UserRepository userRepository;

    /*************** injecting Token object here ***************/
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private  EmailService emailService;

    /*************** adding user details to user repository ***************/
    @Override
    public ResponseEntity<ResponseDTO> createUserProfile(UserDTO userDTO) {
        UserData userData = new UserData(userRepository.findAll().size() + 1, userDTO);
        userRepository.save(userData);
        String token;
        token = tokenUtil.createToken(userData.getUserId());
        emailService.sendEmail("padmajapawar7@gmail,com", "u have registered to book store ", token);
        ResponseDTO respDTO = new ResponseDTO("User registered successfully ", token);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /*************** getting user list from user repository ***************/
    @Override
    public List<UserData> getUserList() {
        return userRepository.findAll();
    }

    /*************** validating user by token ***************/
    @Override
    public ResponseDTO loginValidation(LoginDTO loginDTO) {
        String token;
        Optional<UserData> user = userRepository.findByEmailId(loginDTO.getEmailId());

        if (user.isPresent()) {
            String password = user.get().getPassword();
            if (password.equals(loginDTO.getPassword())) {
                token = tokenUtil.createToken(user.get().getUserId());
                return new ResponseDTO("User is Found", token);
            } else throw new UserRegistrationException("Password is Wrong");
        }
        else {
            throw new UserRegistrationException("Email Id or Password is Wrong");
        }
    }

    /*************** deleting user from user repository ***************/
    @Override
    public void deleteUserData(int userId) {
        userRepository.deleteById(userId);
    }

    /*************** updating user details from user repository ***************/
    @Override
    public ResponseEntity<ResponseDTO> updateUserById(int userId, UserDTO userDTO) {
        List<UserData> userDataList = this.getUserList();
        for (UserData userData : userDataList) {
            if(userData.getUserId() == userId) {
                userData.setName(userDTO.name);
                userData.setAddress(userDTO.address);
                userData.setEmailId(userDTO.emailId);
//                userData.setDateCreated(userDTO.dateCreated);
                userData.setPassword(userDTO.password);

                userRepository.save(userData);
                ResponseDTO respDTO = new ResponseDTO("User Details Updated Successfully ", userData);
                return new ResponseEntity<>(respDTO, HttpStatus.OK);
            } else {
                ResponseDTO respDTO = new ResponseDTO("User not present with given Id ", userId);
                return new ResponseEntity<>(respDTO, HttpStatus.OK);
            }
        }
        return null;
    }

    @Override
    public Optional<UserData> getUserById(int userId) {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<ResponseDTO> getUserByToken(String token) {
        int userId = tokenUtil.decodeToken(token);
        Optional<UserData> user = userRepository.findById(userId);
        if (user.isPresent()) {
            ResponseDTO respDTO = new ResponseDTO("User is present with given Id ", user);
            return new ResponseEntity<>(respDTO, HttpStatus.OK);
        } else
            return null;
    }
}
