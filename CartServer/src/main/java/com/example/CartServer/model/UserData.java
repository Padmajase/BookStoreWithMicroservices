package com.example.CartServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class UserData {

    @Id
    private Integer userId;
    private String name;
    private String emailId;
    private String address;
    private String password;

    public UserData() {
    }

    public UserData(Integer userId, String name, String emailId, String address, String password) {
        this.userId = userId;
        this.name = name;
        this.emailId = emailId;
        this.address = address;
        this.password = password;
    }
}
