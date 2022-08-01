package com.example.OrderServer.model;

import com.example.OrderServer.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Optional;

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

    public String getEmailId() {
        return emailId;
    }

    public String getFirstName() {
        return name;
    }

}
