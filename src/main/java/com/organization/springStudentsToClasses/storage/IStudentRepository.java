package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.models.StudentBase;
import com.organization.springStudentsToClasses.models.StudentWithId;
import java.util.List;

public interface IStudentRepository extends IRepository<StudentBase, StudentWithId> {

  List<StudentWithId> getAllSearch(String firstName, String lastName);
}
