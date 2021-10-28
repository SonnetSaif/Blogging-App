package com.TestAssignment.Blogging_App.users.service;

import com.TestAssignment.Blogging_App.users.dto.LoginRequestDTO;
import com.TestAssignment.Blogging_App.users.dto.LoginResponseDTO;
import com.TestAssignment.Blogging_App.users.entity.UsersEntity;
import com.TestAssignment.Blogging_App.users.repository.UsersRepository;
import com.TestAssignment.Blogging_App.utils.ExceptionHandlerUtil;
import com.TestAssignment.Blogging_App.utils.PasswordEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class UsersService {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsersRepository usersRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public LoginResponseDTO checkUser(LoginRequestDTO requestDTO) throws ExceptionHandlerUtil, NoSuchAlgorithmException {

        UsersEntity entity = usersRepository.findByUserName(requestDTO.getUsername());
        String hashPass = PasswordEncoder.encode(requestDTO.getPassword());

        if (entity == null)
            throw new ExceptionHandlerUtil(HttpStatus.NOT_FOUND, "User not found");

        if (hashPass.equals(entity.getPassword()))
            throw new ExceptionHandlerUtil(HttpStatus.NOT_FOUND, "Password not matched");

        return LoginResponseDTO.builder()
                .userName(entity.getUserName())
                .role(entity.getRole())
                .message("Login is successful")
                .build();
    }
}
