package com.organization.springStudentCourse.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Set;

@JsonPropertyOrder({ "id", "firstName", "lastName", "courses" })
public class FullStudentData extends StudentData {

  private Set<CourseData> courses;

  public FullStudentData(int id, String firstName, String lastName,
      Set<CourseData> courses) {
    super(id,firstName,lastName);
    this.courses = courses;
  }

  public Set<CourseData> getCourses() {
    return courses;
  }

  public void setCourses(
      Set<CourseData> courses) {
    this.courses = courses;
  }
}
