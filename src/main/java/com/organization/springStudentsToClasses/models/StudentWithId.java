package com.organization.springStudentsToClasses.models;

public class StudentWithId extends StudentBase {

  private int id;

  public StudentWithId(int id, String firstName, String lastName) {
    super(firstName, lastName);
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

}
