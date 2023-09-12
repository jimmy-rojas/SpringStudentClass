package com.organization.springStudentCourse.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Set;

@JsonPropertyOrder({ "id", "firstName", "lastName", "courses" })
public class StudentCoursesWrapper extends StudentDTO {

  private Set<CourseDTO> courses;

  public StudentCoursesWrapper(int id, String firstName, String lastName,
                               Set<CourseDTO> courses) {
    super(id,firstName,lastName);
    this.courses = courses;
  }

  public Set<CourseDTO> getCourses() {
    return courses;
  }

  public void setCourses(
      Set<CourseDTO> courses) {
    this.courses = courses;
  }
}
