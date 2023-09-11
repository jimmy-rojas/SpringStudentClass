package com.organization.springStudentsToClasses.services;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassBase;
import com.organization.springStudentsToClasses.models.ClassWithId;
import com.organization.springStudentsToClasses.storage.IClassRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassSaveService is a service class to manage Classes features
 */
@Service
public class ClassSaveService implements IClassRepository {

  private final IClassRepository repository;

  @Autowired
  public ClassSaveService(IClassRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<ClassWithId> getAll() {
    return repository.getAll();
  }

  @Override
  public ClassWithId save(ClassBase classBase) {
    return repository.save(classBase);
  }

  @Override
  public ClassWithId update(int id, ClassBase classBase)
      throws NotFoundException {
    return repository.update(id, classBase);
  }

  @Override
  public void delete(int id) throws NotFoundException {
    repository.delete(id);
  }
}
