package com.organization.springStudentCourse.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.organization.springStudentCourse.exceptions.InvalidOperationException;
import com.organization.springStudentCourse.exceptions.NotFoundException;
import com.organization.springStudentCourse.models.CourseDTO;
import com.organization.springStudentCourse.models.CourseStudentsWrapper;
import com.organization.springStudentCourse.storage.ICourseRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CourseServiceTest {

  private CourseService instance;
  private ICourseRepository courseRepository;
  private CourseStudentsWrapper fullCourseData;

  @Before
  public void setUp() {
    fullCourseData = new CourseStudentsWrapper(1, "code", "title", "description", new HashSet<>());
    courseRepository = new ICourseRepository() {
      @Override
      public List<CourseStudentsWrapper> getAllSearch(String code, String title, String description) {
        List<CourseStudentsWrapper> data = new ArrayList<>();
        data.add(new CourseStudentsWrapper(1, code, title, description, new HashSet<>()));
        return data;
      }

      @Override
      public List<CourseStudentsWrapper> getAll() {
        return new ArrayList<>();
      }

      @Override
      public CourseStudentsWrapper getById(int id) throws NotFoundException {
        return new CourseStudentsWrapper(id, "code", "title", "description", new HashSet<>());
      }

      @Override
      public CourseStudentsWrapper save(CourseStudentsWrapper classBase) {
        return new CourseStudentsWrapper(1, "code", "title", "description", new HashSet<>());
      }

      @Override
      public CourseStudentsWrapper update(CourseStudentsWrapper classBase) throws NotFoundException {
        if (classBase.getId() > 0) {
          return new CourseStudentsWrapper(classBase.getId(), classBase.getCode(), classBase.getTitle(),
              classBase.getDescription(), new HashSet<>());
        }
        throw new NotFoundException("Not Found");
      }

      @Override
      public void delete(int classId) throws NotFoundException, InvalidOperationException {
        if (classId < 1) {
          throw new NotFoundException("Not Found");
        }
      }
    };
    instance = new CourseService(courseRepository);
  }

  @Test
  public void testGetAll() {
    List<CourseDTO> allClasses = instance.getAll();
    assertNotNull(allClasses);
    assertEquals(0, allClasses.size());
  }

  @Test
  public void getAllSearch() throws Exception {
    List<CourseStudentsWrapper> allClasses = instance.getAllSearch("code", "title", "description");
    assertNotNull(allClasses);
    Assert.assertEquals(1, allClasses.size());
  }

  @Test
  public void testCreate() {
    CourseDTO classSaved = instance.save(fullCourseData);
    assertNotNull(classSaved);
    assertEquals(1, classSaved.getId());
  }

  @Test (expected = NotFoundException.class)
  public void testUpdate_NotFoundException() throws Exception {
    fullCourseData.setId(-1);
    instance.update(fullCourseData);
  }

  @Test
  public void testUpdate() throws Exception {
    CourseDTO classUpdated = instance.update(fullCourseData);
    assertNotNull(classUpdated);
    assertEquals(1, classUpdated.getId());
  }

  @Test (expected = NotFoundException.class)
  public void testDelete_NotFoundException() throws Exception {
    instance.delete(-1);
  }

  @Test
  public void testDelete() throws Exception {
    instance.delete(1);
  }
}