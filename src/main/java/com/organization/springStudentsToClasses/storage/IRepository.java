package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.exceptions.InvalidOperationException;
import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import java.util.List;

public interface IRepository<T> {

  List<T> getAll();

  T getById(int id) throws NotFoundException;

  T save(T dataToSave);

  T update(T dataToUpdate) throws NotFoundException;

  void delete(int id) throws NotFoundException, InvalidOperationException;

}
