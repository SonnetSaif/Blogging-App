package com.TestAssignment.Blogging_App.users.controller;

import com.TestAssignment.Blogging_App.users.dto.LoginRequestDTO;
import com.TestAssignment.Blogging_App.users.dto.LoginResponseDTO;
import com.TestAssignment.Blogging_App.users.service.UsersService;
import com.TestAssignment.Blogging_App.utils.ExceptionHandlerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.security.NoSuchAlgorithmException;


@RestController
@RequestMapping("/v1/users")
@Slf4j
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("")
    public ResponseEntity<LoginResponseDTO> createOffice(
//            @RequestHeader(name="Authorization") String token,
            @RequestHeader(name="Request-Id", required= true) @NotEmpty String requestId,
            @RequestHeader(name="Request-Timeout-In-Seconds", required= true) @NotEmpty String requestTimeoutInSeconds,
            @RequestHeader(name="Request-Time", required= true) @NotEmpty @Pattern(regexp = "(19|20)[0-9][0-9]-(0[0-9]|1[0-2])-(0[1-9]|([12][0-9]|3[01]))T([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9].([0-9]{3,6})Z", message = "must match yyyy-MM-ddTHH:mm:ss.SSSSSSZ") String requestTime,
            @Valid @RequestBody LoginRequestDTO requestDTO)
    {
        try{
            LoginResponseDTO response = null;
            try {
                response = usersService.checkUser(requestDTO);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            log.info("Response send for office list: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ExceptionHandlerUtil ex){
            log.error(ex.getMessage(), ex);
            throw new ResponseStatusException(ex.getCode(), ex.getMessage(), ex);
        }
    }
}
