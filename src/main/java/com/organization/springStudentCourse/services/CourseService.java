package com.organization.springStudentCourse.services;

import com.organization.springStudentCourse.exceptions.InvalidOperationException;
import com.organization.springStudentCourse.exceptions.NotFoundException;
import com.organization.springStudentCourse.models.CourseDTO;
import com.organization.springStudentCourse.models.CourseStudentsWrapper;
import com.organization.springStudentCourse.storage.ICourseRepository;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassSaveService is a service class to manage Courses features
 */
@Service
public class CourseService {

  private final ICourseRepository repository;

  @Autowired
  public CourseService(ICourseRepository repository) {
    this.repository = repository;
  }

  public List<CourseDTO> getAll() {
    return repository.getAll()
            .stream()
            .filter(CourseDTO.class::isInstance)
            .map(CourseDTO.class::cast)
            .collect(Collectors.toList());
  }

  public CourseStudentsWrapper getById(int id) throws NotFoundException {
    return repository.getById(id);
  }

  public CourseStudentsWrapper save(CourseDTO courseBase) {
    CourseStudentsWrapper fullCourseData = new CourseStudentsWrapper(0, courseBase.getCode(), courseBase.getTitle(),
        courseBase.getDescription(), new HashSet<>());
    return repository.save(fullCourseData);
  }

  public CourseStudentsWrapper update(CourseDTO courseBase)
      throws NotFoundException {
    CourseStudentsWrapper courseData = getById(courseBase.getId());
    courseData.setCode(courseBase.getCode());
    courseData.setTitle(courseBase.getTitle());
    courseData.setDescription(courseBase.getDescription());
    return repository.update(courseData);
  }

  public void delete(int id)
      throws NotFoundException, InvalidOperationException {
    CourseStudentsWrapper fullCourseData = getById(id);
    if (fullCourseData.getStudents().isEmpty()) {
      repository.delete(id);
    } else {
      throw new InvalidOperationException("Unable to delete course with assigned students");
    }
  }

  public List<CourseStudentsWrapper> getAllSearch(String code, String title, String description) {
    return repository.getAllSearch(code, title, description);
  }
}
