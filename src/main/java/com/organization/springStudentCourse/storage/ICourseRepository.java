package com.organization.springStudentCourse.storage;

import com.organization.springStudentCourse.models.CourseStudentsWrapper;
import java.util.List;

public interface ICourseRepository extends IRepository<CourseStudentsWrapper> {

  List<CourseStudentsWrapper> getAllSearch(String code, String title, String description);
}
