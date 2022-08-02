package com.example.CustomerServer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    public String name;
    public String emailId;
    public String address;
    public String password;

}
