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

    public UserData() {
        super();
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
    public void updateUser(UserDTO userDTO) {
        this.name = userDTO.name;
        this.emailId =userDTO.emailId;
        this.address = userDTO.address;
        this.password  = userDTO.password;
    }
}
