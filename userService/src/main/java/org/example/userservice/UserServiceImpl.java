package org.example.userservice;


import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {

        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(userEntity);

        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);

        return returnValue;
    }

    @Override
    public UserDto getuserByUserId(Long id){
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);

        List<ResponseOrder> orderList = new ArrayList<>();
        userDto.setOrders(orderList);

        return userDto;
    }

    @Override
    public List<UserDto> findAllUsers(){
        Iterable<UserEntity> userEntities = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        userEntities.forEach(v -> {
            UserDto userDto = new ModelMapper().map(v, UserDto.class);
            userDtoList.add(userDto);
        });


        return userDtoList;
    }
}
