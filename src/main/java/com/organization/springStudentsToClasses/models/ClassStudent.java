package com.organization.springStudentsToClasses.models;

import java.util.List;

public final class ClassStudent extends ClassWithId {

  private List<StudentWithId> students;

  public ClassStudent(int id, String code, String title, String description,
      List<StudentWithId> students) {
    super(id, code, title, description);
    this.students = students;
  }

  public List<StudentWithId> getStudents() {
    return students;
  }

  public void setStudents(
      List<StudentWithId> students) {
    this.students = students;
  }
}
