package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.models.StudentBase;
import com.organization.springStudentsToClasses.models.StudentWithId;

public interface IStudentRepository extends IRepository<StudentBase, StudentWithId> {

}
