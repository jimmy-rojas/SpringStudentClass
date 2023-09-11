package com.organization.springStudentsToClasses.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonPropertyOrder({ "id", "code", "title", "description", "students" })
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
