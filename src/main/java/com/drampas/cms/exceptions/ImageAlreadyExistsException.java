package com.drampas.cms.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ImageAlreadyExistsException extends RuntimeException{
    public ImageAlreadyExistsException(String message) {
        super(message);
    }
}
