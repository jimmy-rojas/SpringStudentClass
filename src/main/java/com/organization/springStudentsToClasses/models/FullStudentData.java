package com.organization.springStudentsToClasses.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Set;

@JsonPropertyOrder({ "id", "firstName", "lastName", "classes" })
public class FullStudentData extends StudentData {

  private Set<ClassData> classes;

  public FullStudentData(int id, String firstName, String lastName,
      Set<ClassData> classes) {
    super(id,firstName,lastName);
    this.classes = classes;
  }

  public Set<ClassData> getClasses() {
    return classes;
  }

  public void setClasses(
      Set<ClassData> classes) {
    this.classes = classes;
  }
}
