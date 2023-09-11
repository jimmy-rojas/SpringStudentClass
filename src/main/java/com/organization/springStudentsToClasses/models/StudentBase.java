package com.organization.springStudentsToClasses.models;

public class StudentBase {

  private String firstName;
  private String lastName;

  public StudentBase() {
  }

  public StudentBase(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
