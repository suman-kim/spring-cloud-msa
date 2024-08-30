package org.example.userservice;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/")
public class UserController {

    Environment env;
    private final UserService userService;


    @GetMapping("/health-check")
    public String status() {

        return String.format("It's Working, service.name=%s", env.getProperty("service.name"));
    }


    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody RequestUser user){

        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(user, UserDto.class);

        userService.createUser(userDto);

        return new ResponseEntity<>(userDto, HttpStatus.CREATED);

    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(){
        List<UserDto> userDtoList = userService.findAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(userDtoList);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") Long userId){
        UserDto userDto = userService.getuserByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

}
