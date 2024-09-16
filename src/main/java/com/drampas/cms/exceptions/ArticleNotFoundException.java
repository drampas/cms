package com.drampas.cms.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ArticleNotFoundException extends RuntimeException{

    public ArticleNotFoundException(String message){
        super(message);
    }
}
