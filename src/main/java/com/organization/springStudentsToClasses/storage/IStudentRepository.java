package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.models.FullStudentData;
import java.util.List;

public interface IStudentRepository extends IRepository<FullStudentData> {

  List<FullStudentData> getAllSearch(String firstName, String lastName);
}
