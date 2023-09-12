package com.organization.springStudentCourse.storage;

import com.organization.springStudentCourse.exceptions.NotFoundException;
import com.organization.springStudentCourse.models.StudentCoursesWrapper;
import com.organization.springStudentCourse.models.StudentDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("default")
public class StudentRepository implements IStudentRepository {

  @Autowired
  private MockDataStorage storage;

  @Override
  public List<StudentCoursesWrapper> getAll() {
    return new ArrayList<>(storage.getStudentClassMap().values());
  }

  @Override
  public StudentCoursesWrapper getById(int id)
      throws NotFoundException {
    if (!storage.getStudentClassMap().containsKey(id)) {
      throw new NotFoundException("Unable to find student with id: " + id);
    }
    return storage.getStudentClassMap().get(id);
  }

  @Override
  public StudentCoursesWrapper save(StudentCoursesWrapper student) {
    int newId = storage.counterStudent.incrementAndGet();
    student.setId(newId);
    storage.getStudentClassMap().put(newId, student);
    return student;
  }

  @Override
  public StudentCoursesWrapper update(StudentCoursesWrapper student)
      throws NotFoundException {
    storage.getStudentClassMap().put(student.getId(), student);
    return student;
  }

  @Override
  public void delete(int studentId) throws NotFoundException {
    StudentDTO studentData = getById(studentId);
    storage.getStudentClassMap().remove(studentData.getId());
  }

  @Override
  public List<StudentCoursesWrapper> getAllSearch(String firstName, String lastName) {
    return storage.getStudentClassMap().values()
        .stream()
        .filter((student) ->
          student.getFirstName().equalsIgnoreCase(firstName)
              || student.getLastName().equalsIgnoreCase(lastName)
    ).collect(Collectors.toList());
  }
}
