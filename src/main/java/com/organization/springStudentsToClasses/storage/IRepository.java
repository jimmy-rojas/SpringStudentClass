package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import java.util.List;

public interface IRepository<U, T> {

  List<T> getAll();

  T save(U dataToSave);

  T update(int id, U dataToUpdate) throws NotFoundException;

  void delete(int id) throws NotFoundException;

}
