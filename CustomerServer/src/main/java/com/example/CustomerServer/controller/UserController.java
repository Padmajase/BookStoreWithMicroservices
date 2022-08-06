package com.example.CustomerServer.controller;

import com.example.CustomerServer.dto.LoginDTO;
import com.example.CustomerServer.dto.ResponseDTO;
import com.example.CustomerServer.dto.UserDTO;
import com.example.CustomerServer.model.EmailData;
import com.example.CustomerServer.model.UserData;
import com.example.CustomerServer.rabbitmq.MessageConfig;
import com.example.CustomerServer.util.EmailService;
import com.example.CustomerServer.service.IUserInterface;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/*
adding user login,
sending email,
getting token
 */
@RestController
@RequestMapping("/user")
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


    /*************** registering user ***************/
    @PostMapping("/create/")
    public ResponseEntity<ResponseDTO> addUser(@RequestBody UserDTO userDTO) {
        ResponseEntity<ResponseDTO> userQueue = userInterface.createUserProfile(userDTO);
        rabbitTemplate.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, userQueue);
        return userQueue;
    }

    /*************** getting user list  ***************/
    @RequestMapping(value = {"","/","/list"})
    public ResponseEntity<ResponseDTO> getUserList() {
        List<UserData> listOfUser;
        listOfUser = userInterface.getUserList();
        ResponseDTO responseDTO = new ResponseDTO("registered users list : ", listOfUser);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /*************** get user by ID ***************/
    @GetMapping("/get/{userId}")
    public ResponseEntity<ResponseDTO> updateBookData(@PathVariable("userId") int userId){
        Optional<UserData> userData;
        userData = userInterface.getUserById(userId);
        ResponseDTO responseDTO = new ResponseDTO("user details with given id is : ", userData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }


    /*************** update user by ID ***************/
    @PutMapping("/update/{userId}")
    public ResponseEntity<ResponseDTO> updateBookData(@PathVariable("userId") int userId,
                                                      @RequestBody UserDTO userDTO){
        return  userInterface.updateUserById(userId, userDTO);
    }

    /*************** getting user by token ***************/
    @GetMapping("/getByToken?token=")
    public ResponseEntity<ResponseDTO> getBookById(@RequestParam String token) {
        return userInterface.getUserByToken(token);
    }

    /*************** delete user by its Id ***************/
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ResponseDTO> deleteBookById(@PathVariable int userId){
        userInterface.deleteUserData(userId);
        ResponseDTO respDTO = new ResponseDTO("User Deleted Successfully", "with user Id : " +userId);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /*************** getting email after sign up ***************/
    @PostMapping("/signup/")
    public ResponseEntity signIn(@RequestBody EmailData emailData) {
        emailService.sendEmail(emailData.getToEmail(), emailData.getSubject(), emailData.getBody());
        return ResponseEntity.ok("success");
    }

    /*************** getting token here by user emailId and password ***************/
    @PostMapping("/gettoken/")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO loginDTO) {
        ResponseDTO responseDTO = userInterface.loginValidation(loginDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
