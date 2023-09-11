package com.organization.springStudentCourse.exceptions;

/**
 * NotFoundException is a custom model which stores proper exception message
 */
public class NotFoundException extends Exception {

  public NotFoundException(String message) {
    super(message);
  }

}
