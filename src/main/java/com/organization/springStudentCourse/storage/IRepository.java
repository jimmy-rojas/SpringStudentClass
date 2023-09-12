package com.organization.springStudentCourse.storage;

import com.organization.springStudentCourse.exceptions.InvalidOperationException;
import com.organization.springStudentCourse.exceptions.NotFoundException;
import java.util.List;

public interface IRepository<T, V> {

  List<V> getAll();

  T getById(int id) throws NotFoundException;

  T save(T dataToSave);

  T update(T dataToUpdate) throws NotFoundException;

  void delete(int id) throws NotFoundException, InvalidOperationException;

}
