package com.post.blog.exception;

import com.post.blog.dto.Errordetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Errordetails> handlerResourceNotFoundException(
            ResourceNotFoundException e,
            WebRequest webRequest
    ){
            Errordetails errordetails = new Errordetails(e.getMessage(),new Date(),webRequest.getDescription(true));

            return new ResponseEntity<>(errordetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
