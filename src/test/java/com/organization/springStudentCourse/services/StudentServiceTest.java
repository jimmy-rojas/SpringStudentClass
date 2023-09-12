package com.organization.springStudentCourse.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.organization.springStudentCourse.exceptions.InvalidOperationException;
import com.organization.springStudentCourse.exceptions.NotFoundException;
import com.organization.springStudentCourse.models.CourseStudentsWrapper;
import com.organization.springStudentCourse.models.StudentCoursesWrapper;
import com.organization.springStudentCourse.models.StudentDTO;
import com.organization.springStudentCourse.storage.ICourseRepository;
import com.organization.springStudentCourse.storage.IStudentRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class StudentServiceTest {

  private StudentService instance;
  private IStudentRepository studentRepository;
  private ICourseRepository courseRepository;
  private StudentCoursesWrapper studentBase;

  @Before
  public void setUp() {
    studentBase = new StudentCoursesWrapper(1, "firstName", "lastName", new HashSet<>());
    studentRepository = new IStudentRepository() {
      @Override
      public List<StudentCoursesWrapper> getAllSearch(String firstName, String lastName) {
        List<StudentCoursesWrapper> data = new ArrayList<>();
        data.add(new StudentCoursesWrapper(1, firstName, lastName, new HashSet<>()));
        return data;
      }

      @Override
      public List<StudentCoursesWrapper> getAll() {
        return new ArrayList<>();
      }

      @Override
      public StudentCoursesWrapper getById(int id) throws NotFoundException {
        return new StudentCoursesWrapper(id, "firstName", "lastName", new HashSet<>());
      }

      @Override
      public StudentCoursesWrapper save(StudentCoursesWrapper student) {
        return new StudentCoursesWrapper(1, "firstName", "lastName", new HashSet<>());
      }

      @Override
      public StudentCoursesWrapper update(StudentCoursesWrapper student) throws NotFoundException {
        if (student.getId() > 0) {
          return new StudentCoursesWrapper(student.getId(), "firstName", "lastName", new HashSet<>());
        }
        throw new NotFoundException("Not Found");
      }

      @Override
      public void delete(int studentId) throws NotFoundException, InvalidOperationException {
        if (studentId < 1) {
          throw new NotFoundException("Not Found");
        }
      }
    };
    courseRepository = new ICourseRepository() {
      @Override
      public List<CourseStudentsWrapper> getAllSearch(String code, String title, String description) {
        return null;
      }

      @Override
      public List<CourseStudentsWrapper> getAll() {
        return null;
      }

      @Override
      public CourseStudentsWrapper getById(int id) throws NotFoundException {
        return new CourseStudentsWrapper(id, "code", "title", "desc", new HashSet<>());
      }

      @Override
      public CourseStudentsWrapper save(CourseStudentsWrapper dataToSave) {
        return null;
      }

      @Override
      public CourseStudentsWrapper update(CourseStudentsWrapper dataToUpdate) throws NotFoundException {
        return null;
      }

      @Override
      public void delete(int id) throws NotFoundException, InvalidOperationException {

      }
    };
    instance = new StudentService(studentRepository, courseRepository);
  }

  @Test
  public void testGetAll() throws Exception {
    List<StudentDTO> allStudents = instance.getAll();
    assertNotNull(allStudents);
    assertEquals(0, allStudents.size());
  }

  @Test
  public void getAllSearch() throws Exception {
    List<StudentCoursesWrapper> allStudents = instance.getAllSearch("firstName", "lastName");
    assertNotNull(allStudents);
    assertEquals(1, allStudents.size());
  }

  @Test
  public void testCreate() throws Exception {
    StudentDTO studentSaved = instance.save(studentBase);
    assertNotNull(studentSaved);
    assertEquals(1, studentSaved.getId());
  }

  @Test (expected = NotFoundException.class)
  public void testUpdate_NotFoundException() throws Exception {
    studentBase.setId(-1);
    instance.update(studentBase);
  }

  @Test
  public void testUpdate() throws Exception {
    StudentDTO studentUpdated = instance.update(studentBase);
    assertNotNull(studentUpdated);
    assertEquals(1, studentUpdated.getId());
  }

  @Test (expected = NotFoundException.class)
  public void testDelete_NotFoundException() throws Exception {
    instance.delete(0);
  }

  @Test
  public void testDelete() throws Exception {
    instance.delete(1);
  }
}