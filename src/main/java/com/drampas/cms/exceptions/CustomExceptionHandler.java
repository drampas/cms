package com.drampas.cms.exceptions;


import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler{

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<?> handleArticleNotFound(ArticleNotFoundException exception){
        ErrorResponse response=new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<?> handleImageNotFound(ImageNotFoundException exception){
        ErrorResponse response=new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<?> handleInvalidLogin(InvalidLoginException exception){
        ErrorResponse response=new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UploadFailedException.class)
    public ResponseEntity<?> handleUploadFailed(UploadFailedException exception){
        ErrorResponse response=new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    //java.sql.SQLIntegrityConstraintViolationException
    @ExceptionHandler(ImageAlreadyExistsException.class)
    public ResponseEntity<?> handleImageAlreadyExist(ImageAlreadyExistsException exception){
        ErrorResponse response=new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
