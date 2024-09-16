package com.drampas.cms.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UploadFailedException extends RuntimeException{


    public UploadFailedException(String message) {
        super(message);
    }
}
