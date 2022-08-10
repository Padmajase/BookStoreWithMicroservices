package com.example.OrderServer.dto;

import lombok.Data;

@Data
public class User {

    public Integer userId;
    public String name;
    public String emailId;
    public String address;
    public String password;

    public User() {
    }

    public User(Integer userId, String name, String emailId, String address, String password) {
        this.userId = userId;
        this.name = name;
        this.emailId = emailId;
        this.address = address;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
}
