package com.organization.springStudentsToClasses.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.organization.springStudentsToClasses.exceptions.InvalidOperationException;
import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.FullClassData;
import com.organization.springStudentsToClasses.models.FullStudentData;
import com.organization.springStudentsToClasses.models.StudentData;
import com.organization.springStudentsToClasses.storage.IClassRepository;
import com.organization.springStudentsToClasses.storage.IStudentRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class StudentSaveServiceTest {

  private StudentSaveService instance;
  private IStudentRepository studentRepository;
  private IClassRepository classRepository;
  private FullStudentData studentBase;

  @Before
  public void setUp() {
    studentBase = new FullStudentData(1, "firstName", "lastName", new HashSet<>());
    studentRepository = new IStudentRepository() {
      @Override
      public List<FullStudentData> getAllSearch(String firstName, String lastName) {
        List<FullStudentData> data = new ArrayList<>();
        data.add(new FullStudentData(1, firstName, lastName, new HashSet<>()));
        return data;
      }

      @Override
      public List<FullStudentData> getAll() {
        return new ArrayList<>();
      }

      @Override
      public FullStudentData getById(int id) throws NotFoundException {
        return new FullStudentData(id, "firstName", "lastName", new HashSet<>());
      }

      @Override
      public FullStudentData save(FullStudentData student) {
        return new FullStudentData(1, "firstName", "lastName", new HashSet<>());
      }

      @Override
      public FullStudentData update(FullStudentData student) throws NotFoundException {
        if (student.getId() > 0) {
          return new FullStudentData(student.getId(), "firstName", "lastName", new HashSet<>());
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
    classRepository = new IClassRepository() {
      @Override
      public List<FullClassData> getAllSearch(String code, String title, String description) {
        return null;
      }

      @Override
      public List<FullClassData> getAll() {
        return null;
      }

      @Override
      public FullClassData getById(int id) throws NotFoundException {
        return new FullClassData(id, "code", "title", "desc", new HashSet<>());
      }

      @Override
      public FullClassData save(FullClassData dataToSave) {
        return null;
      }

      @Override
      public FullClassData update(FullClassData dataToUpdate) throws NotFoundException {
        return null;
      }

      @Override
      public void delete(int id) throws NotFoundException, InvalidOperationException {

      }
    };
    instance = new StudentSaveService(studentRepository, classRepository);
  }

  @Test
  public void testGetAll() throws Exception {
    List<FullStudentData> allStudents = instance.getAll();
    assertNotNull(allStudents);
    assertEquals(0, allStudents.size());
  }

  @Test
  public void getAllSearch() throws Exception {
    List<FullStudentData> allStudents = instance.getAllSearch("firstName", "lastName");
    assertNotNull(allStudents);
    assertEquals(1, allStudents.size());
  }

  @Test
  public void testCreate() throws Exception {
    StudentData studentSaved = instance.save(studentBase);
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
    StudentData studentUpdated = instance.update(studentBase);
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