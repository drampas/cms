package com.drampas.cms.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class InvalidArticleException extends RuntimeException{

    public InvalidArticleException(String message) {
        super(message);
    }
}
