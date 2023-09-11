package com.organization.springStudentCourse.storage;

import com.organization.springStudentCourse.models.FullCourseData;
import java.util.List;

public interface ICourseRepository extends IRepository<FullCourseData> {

  List<FullCourseData> getAllSearch(String code, String title, String description);
}
