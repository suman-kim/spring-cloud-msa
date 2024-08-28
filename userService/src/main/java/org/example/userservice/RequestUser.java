package org.example.userservice;


import lombok.Data;
import lombok.NonNull;


@Data
public class RequestUser {

    private String email;
    private String name;
    private String password;
}
