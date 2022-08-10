package com.example.CustomerServer.controller;

import com.example.CustomerServer.dto.LoginDTO;
import com.example.CustomerServer.dto.ResponseDTO;
import com.example.CustomerServer.dto.UserDTO;
import com.example.CustomerServer.model.EmailData;
import com.example.CustomerServer.model.UserData;
import com.example.CustomerServer.rabbitmq.MessageConfig;
import com.example.CustomerServer.util.EmailService;
import com.example.CustomerServer.service.IUserInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/*
implementing CURD operations for user module
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    /*************** injecting Email service Object ***************/
    @Autowired
    EmailService emailService;

    /*************** injecting User Interface Object ***************/
    @Autowired
    private IUserInterface userInterface;

    /*************** injecting Rabbit Template Object ***************/
    @Autowired
    RabbitTemplate rabbitTemplate;


    /*************** creating / registering user ***************/
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addUser(@RequestBody UserDTO userDTO) {
        ResponseEntity<ResponseDTO> userQueue = userInterface.createUserProfile(userDTO);
        rabbitTemplate.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, userQueue);
        return userQueue;
    }

    /*************** getting user list  ***************/
    @RequestMapping(value = {"","/","/get"})
    public ResponseEntity<ResponseDTO> getUserList() {
        List<UserData> listOfUser;
        listOfUser = userInterface.getUserList();
        ResponseDTO responseDTO = new ResponseDTO("registered users list : ", listOfUser);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /*************** get user by ID ***************/
    @GetMapping("/get/{userId}")
    public ResponseEntity<ResponseDTO> updateBookData(@PathVariable Integer userId){
        UserData userData;
        userData = userInterface.getUserById(userId);
        ResponseDTO responseDTO = new ResponseDTO("user details with given id is : ", userData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    /*************** getting user by token ***************/
    @GetMapping("/getByToken?token=")
    public ResponseEntity<ResponseDTO> getBookById(@RequestParam String token) {
        return userInterface.getUserByToken(token);
    }

    /*************** update user by ID ***************/
    @PutMapping("/update/{userId}")
    public ResponseEntity<ResponseDTO> updateBookData(@PathVariable("userId") Integer userId,
                                                      @RequestBody UserDTO userDTO){
        return  userInterface.updateUserById(userId, userDTO);
    }

    /*************** delete user by its Id ***************/
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ResponseDTO> deleteBookById(@PathVariable Integer userId){
        userInterface.deleteUserData(userId);
        ResponseDTO respDTO = new ResponseDTO("User Deleted Successfully", "with user Id : " +userId);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
}
