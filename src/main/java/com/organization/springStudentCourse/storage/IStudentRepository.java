package com.organization.springStudentCourse.storage;

import com.organization.springStudentCourse.models.StudentCoursesWrapper;
import com.organization.springStudentCourse.models.StudentDTO;

import java.util.List;

public interface IStudentRepository extends IRepository<StudentCoursesWrapper, StudentDTO> {

  List<StudentCoursesWrapper> getAllSearch(String firstName, String lastName);
}
