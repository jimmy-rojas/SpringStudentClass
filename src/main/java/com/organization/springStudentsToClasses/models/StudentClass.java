package com.organization.springStudentsToClasses.models;

import java.util.List;

public final class StudentClass extends StudentWithId {

  private List<ClassWithId> classes;

  public StudentClass(int id, String firstName, String lastName,
      List<ClassWithId> classes) {
    super(id, firstName, lastName);
    this.classes = classes;
  }

  public List<ClassWithId> getClasses() {
    return classes;
  }

  public void setClasses(
      List<ClassWithId> classes) {
    this.classes = classes;
  }
}
