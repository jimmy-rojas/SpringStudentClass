package com.organization.springStudentCourse.storage;

import com.organization.springStudentCourse.models.FullStudentData;
import java.util.List;

public interface IStudentRepository extends IRepository<FullStudentData> {

  List<FullStudentData> getAllSearch(String firstName, String lastName);
}
