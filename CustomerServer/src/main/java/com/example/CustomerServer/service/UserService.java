package com.example.CustomerServer.service;

import com.example.CustomerServer.dto.ResponseDTO;
import com.example.CustomerServer.dto.UserDTO;
import com.example.CustomerServer.exception.UserRegistrationException;
import com.example.CustomerServer.model.UserData;
import com.example.CustomerServer.repository.UserRepository;
import com.example.CustomerServer.token.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.CustomerServer.util.EmailService;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUserInterface{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    EmailService emailService;

    /*************** adding user details to user repository ***************/
    @Override
    public ResponseEntity<ResponseDTO> createUserProfile(UserDTO userDTO) {
        UserData userData = new UserData(userDTO);
        userRepository.save(userData);
        String token;
        token = tokenUtil.createToken(userData.getUserId());
        emailService.sendEmail("padmajapawar7@gmail.com", "u have registered to book store ", token);
        ResponseDTO respDTO = new ResponseDTO("User registered successfully ", userData);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /*************** getting user list from user repository ***************/
    @Override
    public List<UserData> getUserList() {
        if(userRepository.findAll().isEmpty())
            throw new UserRegistrationException("No Users Registered till");
        else
            return userRepository.findAll();
    }

    /*************** getting user by its id ***************/
    @Override
    public UserData getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(()
                -> new UserRegistrationException("User  with id " + userId + " does not exist in database..!"));
    }

    /*************** getting user by its token ***************/
    @Override
    public ResponseEntity<ResponseDTO> getUserByToken(String token) {
        Integer userId = tokenUtil.decodeToken(token);
        Optional<UserData> user = userRepository.findById(userId);
        if (user.isPresent()) {
            ResponseDTO respDTO = new ResponseDTO("User is present with given Id ", user);
            return new ResponseEntity<>(respDTO, HttpStatus.OK);
        } else
            return null;
    }

    /*************** updating user details from user repository ***************/
    @Override
    public ResponseEntity<ResponseDTO> updateUserById(Integer userId, UserDTO userDTO) {
        UserData user = this.getUserById(userId);
        if(user == null) {
            ResponseDTO respDTO = new ResponseDTO("User not present with given Id ", userId);
            return new ResponseEntity<>(respDTO, HttpStatus.OK);
        } else {
            user.updateUser(userDTO);
            userRepository.save(user);
            ResponseDTO respDTO = new ResponseDTO("User Details Updated Successfully ", user);
            return new ResponseEntity<>(respDTO, HttpStatus.OK);
        }
    }

    /*************** deleting user from user repository ***************/
    @Override
    public void deleteUserData(Integer userId) {
        userRepository.deleteById(userId);
    }


    /*************** validating user by token ***************/
//    @Override
//    public ResponseDTO loginValidation(LoginDTO loginDTO) {
//        String token;
//        Optional<UserData> user = userRepository.findByEmailId(loginDTO.getEmailId());
//
//        if (user.isPresent()) {
//            String password = user.get().getPassword();
//            if (password.equals(loginDTO.getPassword())) {
//                token = tokenUtil.createToken(user.get().getUserId());
//                return new ResponseDTO("User is Found", token);
//            } else throw new UserRegistrationException("Password is Wrong");
//        }
//        else {
//            throw new UserRegistrationException("Email Id or Password is Wrong");
//        }
//    }
}
