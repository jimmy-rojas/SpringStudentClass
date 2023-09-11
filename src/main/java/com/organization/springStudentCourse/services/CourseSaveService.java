package com.organization.springStudentCourse.services;

import com.organization.springStudentCourse.exceptions.InvalidOperationException;
import com.organization.springStudentCourse.exceptions.NotFoundException;
import com.organization.springStudentCourse.models.CourseData;
import com.organization.springStudentCourse.models.FullCourseData;
import com.organization.springStudentCourse.storage.ICourseRepository;

import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassSaveService is a service class to manage Classes features
 */
@Service
public class CourseSaveService {

  private final ICourseRepository repository;

  @Autowired
  public CourseSaveService(ICourseRepository repository) {
    this.repository = repository;
  }

  public List<FullCourseData> getAll() {
    return repository.getAll();
  }

  public FullCourseData getById(int id) throws NotFoundException {
    return repository.getById(id);
  }

  public FullCourseData save(CourseData courseBase) {
    FullCourseData fullCourseData = new FullCourseData(0, courseBase.getCode(), courseBase.getTitle(),
        courseBase.getDescription(), new HashSet<>());
    return repository.save(fullCourseData);
  }

  public FullCourseData update(CourseData courseBase)
      throws NotFoundException {
    FullCourseData courseData = getById(courseBase.getId());
    courseData.setCode(courseBase.getCode());
    courseData.setTitle(courseBase.getTitle());
    courseData.setDescription(courseBase.getDescription());
    return repository.update(courseData);
  }

  public void delete(int id)
      throws NotFoundException, InvalidOperationException {
    FullCourseData fullCourseData = getById(id);
    if (fullCourseData.getStudents().isEmpty()) {
      repository.delete(id);
    } else {
      throw new InvalidOperationException("Unable to delete course with assigned students");
    }
  }

  public List<FullCourseData> getAllSearch(String code, String title, String description) {
    return repository.getAllSearch(code, title, description);
  }
}
