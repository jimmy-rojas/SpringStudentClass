package com.organization.springStudentsToClasses.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "code", "title", "description" })
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
