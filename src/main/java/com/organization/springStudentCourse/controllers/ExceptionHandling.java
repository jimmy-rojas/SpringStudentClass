package com.organization.springStudentCourse.controllers;

import com.organization.springStudentCourse.exceptions.InvalidOperationException;
import com.organization.springStudentCourse.exceptions.NotFoundException;
import com.organization.springStudentCourse.models.CustomErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  Logger logger = LoggerFactory.getLogger(ExceptionHandling.class);

  @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Unknown System Error")
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Exception> unknownException(Exception ex) {
    logger.error(ex.getMessage());
    return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public @ResponseBody CustomErrorResponse notFoundException(NotFoundException ex) {
    logger.error(ex.getMessage());
    return new CustomErrorResponse("An error occurred.", ex.getMessage());
  }

  @ExceptionHandler(InvalidOperationException.class)
  @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
  public @ResponseBody CustomErrorResponse invalidOperationException(InvalidOperationException ex) {
    logger.error(ex.getMessage());
    return new CustomErrorResponse("An invalid operation occurred.", ex.getMessage());
  }
}
