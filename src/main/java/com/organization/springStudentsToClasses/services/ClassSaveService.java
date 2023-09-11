package com.organization.springStudentsToClasses.services;

import com.organization.springStudentsToClasses.exceptions.InvalidOperationException;
import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassData;
import com.organization.springStudentsToClasses.models.FullClassData;
import com.organization.springStudentsToClasses.storage.IClassRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassSaveService is a service class to manage Classes features
 */
@Service
public class ClassSaveService {

  private final IClassRepository repository;

  @Autowired
  public ClassSaveService(IClassRepository repository) {
    this.repository = repository;
  }

  public List<FullClassData> getAll() {
    return repository.getAll();
  }

  public FullClassData getById(int id) throws NotFoundException {
    return repository.getById(id);
  }

  public FullClassData save(ClassData classBase) {
    FullClassData classData = new FullClassData(0, classBase.getCode(), classBase.getTitle(),
        classBase.getDescription(), new HashSet<>());
    return repository.save(classData);
  }

  public FullClassData update(ClassData classBase)
      throws NotFoundException {
    FullClassData classData = getById(classBase.getId());
    classData.setCode(classBase.getCode());
    classData.setTitle(classBase.getTitle());
    classData.setDescription(classBase.getDescription());
    return repository.update(classData);
  }

  public void delete(int id)
      throws NotFoundException, InvalidOperationException {
    FullClassData classData = getById(id);
    if (classData.getStudents().isEmpty()) {
      repository.delete(id);
    } else {
      throw new InvalidOperationException("Unable to delete class with assigned students");
    }
  }

  public List<FullClassData> getAllSearch(String code, String title, String description) {
    return repository.getAllSearch(code, title, description);
  }
}
