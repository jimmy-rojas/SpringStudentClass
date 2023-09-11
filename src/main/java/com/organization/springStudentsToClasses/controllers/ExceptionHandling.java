package com.organization.springStudentsToClasses.controllers;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ExceptionHandlingController handles application's exceptions and returns proper responses
 */
@ControllerAdvice
public class ExceptionHandling {

  @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Unknown System Error")
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Exception> unknownException(Exception ex) {
    return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public @ResponseBody CustomErrorResponse notFoundException(NotFoundException ex) {
    return new CustomErrorResponse("Not Found Exception.", ex.getMessage());
  }
}
