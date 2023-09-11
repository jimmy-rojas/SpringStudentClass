package com.organization.springStudentCourse.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.organization.springStudentCourse.exceptions.InvalidOperationException;
import com.organization.springStudentCourse.exceptions.NotFoundException;
import com.organization.springStudentCourse.models.CourseData;
import com.organization.springStudentCourse.models.FullCourseData;
import com.organization.springStudentCourse.storage.ICourseRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CourseSaveServiceTest {

  private CourseSaveService instance;
  private ICourseRepository courseRepository;
  private FullCourseData fullCourseData;

  @Before
  public void setUp() {
    fullCourseData = new FullCourseData(1, "code", "title", "description", new HashSet<>());
    courseRepository = new ICourseRepository() {
      @Override
      public List<FullCourseData> getAllSearch(String code, String title, String description) {
        List<FullCourseData> data = new ArrayList<>();
        data.add(new FullCourseData(1, code, title, description, new HashSet<>()));
        return data;
      }

      @Override
      public List<FullCourseData> getAll() {
        return new ArrayList<>();
      }

      @Override
      public FullCourseData getById(int id) throws NotFoundException {
        return new FullCourseData(id, "code", "title", "description", new HashSet<>());
      }

      @Override
      public FullCourseData save(FullCourseData classBase) {
        return new FullCourseData(1, "code", "title", "description", new HashSet<>());
      }

      @Override
      public FullCourseData update(FullCourseData classBase) throws NotFoundException {
        if (classBase.getId() > 0) {
          return new FullCourseData(classBase.getId(), classBase.getCode(), classBase.getTitle(),
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
    instance = new CourseSaveService(courseRepository);
  }

  @Test
  public void testGetAll() {
    List<FullCourseData> allClasses = instance.getAll();
    assertNotNull(allClasses);
    assertEquals(0, allClasses.size());
  }

  @Test
  public void getAllSearch() throws Exception {
    List<FullCourseData> allClasses = instance.getAllSearch("code", "title", "description");
    assertNotNull(allClasses);
    Assert.assertEquals(1, allClasses.size());
  }

  @Test
  public void testCreate() {
    CourseData classSaved = instance.save(fullCourseData);
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
    CourseData classUpdated = instance.update(fullCourseData);
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