package com.organization.springStudentCourse.exceptions;

/**
 * InvalidOperationException is a custom model which stores proper exception message
 */
public class InvalidOperationException extends Exception {

  public InvalidOperationException(String message) {
    super(message);
  }

}
