package com.example.CustomerServer.model;

import com.example.CustomerServer.dto.UserDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class UserData {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    public Integer userId;

    public String name;
    public String emailId;
    public String address;
    public String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserData() {
    }

    public UserData(Integer userId, UserDTO userDTO) {
        this.userId = userId;
        this.name = userDTO.name;
        this.emailId = userDTO.emailId;
        this.address = userDTO.address;
        this.password = userDTO.password;
    }

    public UserData(UserDTO userDTO) {
        this.name = userDTO.name;
        this.emailId =userDTO.emailId;
        this.address = userDTO.address;
        this.password = userDTO.password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void updateUser(UserDTO userDTO) {
        this.name = userDTO.name;
        this.emailId =userDTO.emailId;
        this.address = userDTO.address;
        this.password  = userDTO.password;
    }
}
