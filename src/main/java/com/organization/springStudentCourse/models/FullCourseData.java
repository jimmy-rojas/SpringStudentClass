package com.organization.springStudentCourse.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Set;

@JsonPropertyOrder({ "id", "code", "title", "description", "students" })
public class FullCourseData extends CourseData {

  private Set<StudentData> students;

  public FullCourseData(int id, String code, String title, String description,
                        Set<StudentData> students) {
    super(id, code, title, description);
    this.students = students;
  }

  public Set<StudentData> getStudents() {
    return students;
  }

  public void setStudents(
      Set<StudentData> students) {
    this.students = students;
  }
}
