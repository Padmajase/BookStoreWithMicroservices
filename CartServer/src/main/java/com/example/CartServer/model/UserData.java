package com.example.CartServer.model;

import com.example.CartServer.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String name;
    private String emailId;
    private String address;
    private String dateCreated;
    private String password;

    public UserData(int userId, UserDTO userDTO) {
        this.userId = userId;
        this.name = userDTO.name;
        this.emailId = userDTO.emailId;
        this.address = userDTO.address;
        this.dateCreated = userDTO.dateCreated;
        this.password = userDTO.password;
    }

    public int getUserId() {
        return userId;
    }
    public String getPassword() {
        return password;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getFirstName() {
        return name;
    }
}
