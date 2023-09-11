package com.organization.springStudentsToClasses.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.StudentBase;
import com.organization.springStudentsToClasses.models.StudentWithId;
import com.organization.springStudentsToClasses.storage.IStudentRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class StudentSaveServiceTest {

  private StudentSaveService instance;
  private IStudentRepository studentRepository;
  private StudentBase studentBase;

  @Before
  public void setUp() {
    studentBase = new StudentBase("firstName", "lastName");
    studentRepository = new IStudentRepository() {
      @Override
      public List<StudentWithId> getAllSearch(String firstName, String lastName) {
        List<StudentWithId> data = new ArrayList<>();
        data.add(new StudentWithId(1, firstName, lastName));
        return data;
      }

      @Override
      public List<StudentWithId> getAll() {
        return new ArrayList<>();
      }

      @Override
      public StudentWithId save(StudentBase student) {
        return new StudentWithId(1, "firstName", "lastName");
      }

      @Override
      public StudentWithId update(int id, StudentBase student) throws NotFoundException {
        if (id > 0) {
          return new StudentWithId(id, "firstName", "lastName");
        }
        throw new NotFoundException("Not Found");
      }

      @Override
      public void delete(int studentId) throws NotFoundException {
        if (studentId < 1) {
          throw new NotFoundException("Not Found");
        }
      }
    };
    instance = new StudentSaveService(studentRepository);
  }

  @Test
  public void testGetAll() throws Exception {
    List<StudentWithId> allStudents = instance.getAll();
    assertNotNull(allStudents);
    assertEquals(0, allStudents.size());
  }

  @Test
  public void getAllSearch() throws Exception {
    List<StudentWithId> allStudents = instance.getAllSearch("firstName", "lastName");
    assertNotNull(allStudents);
    assertEquals(1, allStudents.size());
  }

  @Test
  public void testCreate() throws Exception {
    StudentWithId studentSaved = instance.save(studentBase);
    assertNotNull(studentSaved);
    assertEquals(1, studentSaved.getId());
  }

  @Test (expected = NotFoundException.class)
  public void testUpdate_NotFoundException() throws Exception {
    instance.update(0, studentBase);
  }

  @Test
  public void testUpdate() throws Exception {
    StudentWithId studentUpdated = instance.update(1, studentBase);
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