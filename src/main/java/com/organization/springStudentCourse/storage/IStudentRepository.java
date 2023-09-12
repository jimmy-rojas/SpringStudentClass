package com.organization.springStudentCourse.storage;

import com.organization.springStudentCourse.models.StudentCoursesWrapper;
import java.util.List;

public interface IStudentRepository extends IRepository<StudentCoursesWrapper> {

  List<StudentCoursesWrapper> getAllSearch(String firstName, String lastName);
}
