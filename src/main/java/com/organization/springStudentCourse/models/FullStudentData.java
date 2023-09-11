package com.organization.springStudentCourse.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Set;

@JsonPropertyOrder({ "id", "firstName", "lastName", "classes" })
public class FullStudentData extends StudentData {

  private Set<CourseData> classes;

  public FullStudentData(int id, String firstName, String lastName,
      Set<CourseData> classes) {
    super(id,firstName,lastName);
    this.classes = classes;
  }

  public Set<CourseData> getClasses() {
    return classes;
  }

  public void setClasses(
      Set<CourseData> classes) {
    this.classes = classes;
  }
}
