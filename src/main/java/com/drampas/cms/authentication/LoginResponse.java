package com.drampas.cms.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String token;

    @Override
    public String toString(){
        return "JWTLoginSuccessResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
