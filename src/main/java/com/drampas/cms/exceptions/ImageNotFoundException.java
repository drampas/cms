package com.drampas.cms.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ImageNotFoundException extends RuntimeException{

    public ImageNotFoundException(String message){
        super(message);
    }
}
