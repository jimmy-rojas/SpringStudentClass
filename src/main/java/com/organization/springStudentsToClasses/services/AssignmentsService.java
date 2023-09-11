package com.organization.springStudentsToClasses.services;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassStudent;
import com.organization.springStudentsToClasses.models.StudentClass;
import com.organization.springStudentsToClasses.storage.IAssignmentsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AssignmentsService is a service class to manage assignments for Students and Classes
 */
@Service
public class AssignmentsService implements IAssignmentsRepository {

  private IAssignmentsRepository repository;

  @Autowired
  public AssignmentsService(IAssignmentsRepository repository) {
    this.repository = repository;
  }

  @Override
  public ClassStudent getClassStudents(int id)
      throws NotFoundException {
    return this.repository.getClassStudents(id);
  }

  @Override
  public List<ClassStudent> getAllClasses() {
    return this.repository.getAllClasses();
  }

  @Override
  public StudentClass getStudentClasses(int id)
      throws NotFoundException {
    return this.repository.getStudentClasses(id);
  }

  @Override
  public List<StudentClass> getAllStudents() {
    return this.repository.getAllStudents();
  }
}
