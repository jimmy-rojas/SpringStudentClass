package com.organization.springStudentCourse.storage;

import com.organization.springStudentCourse.models.CourseDTO;
import com.organization.springStudentCourse.models.CourseStudentsWrapper;
import java.util.List;

public interface ICourseRepository extends IRepository<CourseStudentsWrapper, CourseDTO> {

  List<CourseStudentsWrapper> getAllSearch(String code, String title, String description);
}
