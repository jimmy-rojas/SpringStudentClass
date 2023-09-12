package com.organization.springStudentCourse.services;

import com.organization.springStudentCourse.exceptions.InvalidOperationException;
import com.organization.springStudentCourse.exceptions.NotFoundException;
import com.organization.springStudentCourse.models.CourseDTO;
import com.organization.springStudentCourse.models.CourseStudentsWrapper;
import com.organization.springStudentCourse.models.StudentCoursesWrapper;
import com.organization.springStudentCourse.models.StudentDTO;
import com.organization.springStudentCourse.storage.ICourseRepository;
import com.organization.springStudentCourse.storage.IStudentRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StudentSaveService is a service class to manage Students features
 */
@Service
public class StudentService {

  Logger logger = LoggerFactory.getLogger(StudentService.class);

  private final IStudentRepository repository;
  private final ICourseRepository classRepository;

  @Autowired
  public StudentService(IStudentRepository repository, ICourseRepository classRepository) {
    this.repository = repository;
    this.classRepository = classRepository;
  }

  public List<StudentDTO> getAll() {
    return repository.getAll();
  }

  public StudentCoursesWrapper getById(int id) throws NotFoundException {
    return repository.getById(id);
  }

  public StudentCoursesWrapper save(StudentDTO studentBase) {
    StudentCoursesWrapper studentData = new StudentCoursesWrapper(0, studentBase.getFirstName(),
        studentBase.getLastName(), new HashSet<>());
    return repository.save(studentData);
  }

  public StudentCoursesWrapper update(StudentDTO studentBase)
      throws NotFoundException {
    StudentCoursesWrapper studentData = getById(studentBase.getId());
    studentData.setFirstName(studentBase.getFirstName());
    studentData.setLastName(studentBase.getLastName());
    return repository.update(studentData);
  }

  public void delete(int id)
      throws NotFoundException, InvalidOperationException {
    StudentCoursesWrapper studentData = getById(id);
    if (studentData.getCourses().isEmpty()) {
      repository.delete(id);
    } else {
      throw new InvalidOperationException("Unable to delete student with assigned classes");
    }
  }

  public List<StudentCoursesWrapper> getAllSearch(String firstName, String lastName) {
    return repository.getAllSearch(firstName, lastName);
  }

  public StudentCoursesWrapper assignClassesToStudent(int id, Set<Integer> classIds) throws NotFoundException {
    StudentCoursesWrapper studentData = getById(id);
    Set<CourseDTO> classes = new HashSet<>();
    classIds.forEach(classId -> {
      try {
        CourseStudentsWrapper fullClassData = classRepository.getById(classId);
        classes.add(fullClassData);
      } catch (NotFoundException e) {
        logger.warn("Unable to find class with id: " + classId);
      }
    });
    studentData.setCourses(classes);
    return repository.update(studentData);
  }
}
