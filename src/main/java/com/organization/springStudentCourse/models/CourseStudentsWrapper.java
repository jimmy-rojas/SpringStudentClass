package com.organization.springStudentCourse.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Set;

@JsonPropertyOrder({ "id", "code", "title", "description", "students" })
public class CourseStudentsWrapper extends CourseDTO {

  private Set<StudentDTO> students;

  public CourseStudentsWrapper(int id, String code, String title, String description,
                               Set<StudentDTO> students) {
    super(id, code, title, description);
    this.students = students;
  }

  public Set<StudentDTO> getStudents() {
    return students;
  }

  public void setStudents(
      Set<StudentDTO> students) {
    this.students = students;
  }
}
