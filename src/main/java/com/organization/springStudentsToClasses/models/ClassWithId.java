package com.organization.springStudentsToClasses.models;

public class ClassWithId extends ClassBase {

  private int id;

  public ClassWithId(int id, String code, String title, String description) {
    super(code, title, description);
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
