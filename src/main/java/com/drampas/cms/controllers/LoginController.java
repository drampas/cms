package com.drampas.cms.controllers;

import com.drampas.cms.authentication.AuthenticationService;
import com.drampas.cms.authentication.LoginRequest;
import com.drampas.cms.authentication.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
        LoginResponse response= authenticationService.login(loginRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
