package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassStudent;
import com.organization.springStudentsToClasses.models.StudentClass;
import java.util.List;

public interface IAssignmentsRepository {

  ClassStudent getClassStudents(int id) throws NotFoundException;

  List<ClassStudent> getAllClasses();

  StudentClass getStudentClasses(int id) throws NotFoundException;

  List<StudentClass> getAllStudents();
}
