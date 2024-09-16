package com.drampas.cms.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidLoginException extends RuntimeException{

    public InvalidLoginException(String message){
        super(message);
    }
}
