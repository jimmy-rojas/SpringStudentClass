package com.organization.springStudentsToClasses.services;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.StudentBase;
import com.organization.springStudentsToClasses.models.StudentWithId;
import com.organization.springStudentsToClasses.storage.IStudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StudentSaveService is a service class to manage Students features
 */
@Service
public class StudentSaveService implements IStudentRepository {

  private final IStudentRepository repository;

  @Autowired
  public StudentSaveService(IStudentRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<StudentWithId> getAll() {
    return repository.getAll();
  }

  @Override
  public StudentWithId save(StudentBase studentBase) {
    return repository.save(studentBase);
  }

  @Override
  public StudentWithId update(int id, StudentBase studentBase)
      throws NotFoundException {
    return repository.update(id, studentBase);
  }

  @Override
  public void delete(int id) throws NotFoundException {
    repository.delete(id);
  }
}
