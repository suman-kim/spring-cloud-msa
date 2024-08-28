package org.example.userservice;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private String email;
    private String name;
    private String password;
    private Date createdAt;
    private String encryptedPassword;
    private List<ResponseOrder> orders;
}
