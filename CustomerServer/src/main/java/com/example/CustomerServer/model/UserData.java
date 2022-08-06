package com.example.CustomerServer.model;

import com.example.CustomerServer.dto.UserDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "user_data")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String name;
    private String emailId;
    private String address;
    private String password;

    public UserData(int userId, UserDTO userDTO) {
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
}
