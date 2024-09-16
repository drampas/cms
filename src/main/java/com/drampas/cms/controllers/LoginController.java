package com.drampas.cms.controllers;

import com.drampas.cms.authentication.AuthenticationService;
import com.drampas.cms.authentication.LoginRequest;
import com.drampas.cms.authentication.LoginResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Operation(description = "Admin login")
    @SecurityRequirements()
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
        LoginResponse response= authenticationService.login(loginRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
