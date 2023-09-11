package com.organization.springStudentsToClasses.models;

public class ClassBase {

  private String code;
  private String title;
  private String description;

  public ClassBase() {
  }

  public ClassBase(String code, String title, String description) {
    this.code = code;
    this.title = title;
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
