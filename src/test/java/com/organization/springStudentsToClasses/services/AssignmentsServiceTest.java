package com.organization.springStudentsToClasses.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassStudent;
import com.organization.springStudentsToClasses.models.StudentClass;
import com.organization.springStudentsToClasses.storage.IAssignmentsRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class AssignmentsServiceTest {

  private AssignmentsService instance;
  private IAssignmentsRepository repository;

  @Before
  public void setUp() {
    repository = new IAssignmentsRepository() {
      @Override
      public ClassStudent getClassStudents(int id) throws NotFoundException {
        if (id > 0) {
          return new ClassStudent(id, "code", "title", "description",
              new ArrayList<>());
        }
        throw new NotFoundException("Not Found");
      }

      @Override
      public List<ClassStudent> getAllClasses() {
        return new ArrayList<>();
      }

      @Override
      public StudentClass getStudentClasses(int id) throws NotFoundException {
        if (id > 0) {
          return new StudentClass(id,"firstName", "lastName",
              new ArrayList<>());
        }
        throw new NotFoundException("Not Found");
      }

      @Override
      public List<StudentClass> getAllStudents() {
        return new ArrayList<>();
      }
    };
    instance = new AssignmentsService(repository);
  }

  @Test
  public void getAllStudents() throws Exception {
    List<StudentClass> allStudentsList = instance.getAllStudents();
    assertNotNull(allStudentsList);
    assertEquals(0, allStudentsList.size());
  }

  @Test (expected = NotFoundException.class)
  public void getStudentClasses_NotFoundException() throws Exception {
    instance.getStudentClasses(-1);
  }

  @Test
  public void getStudentClasses() throws Exception {
    StudentClass studentClasses = instance.getStudentClasses(1);
    assertNotNull(studentClasses);
    assertEquals(1, studentClasses.getId());
  }

  @Test
  public void getAllClasses() throws Exception {
    List<ClassStudent> allClassesList = instance.getAllClasses();
    assertNotNull(allClassesList);
    assertEquals(0, allClassesList.size());
  }

  @Test (expected = NotFoundException.class)
  public void getClassStudents_NotFoundException() throws Exception {
    instance.getClassStudents(0);
  }

  @Test
  public void getClassStudents() throws Exception {
    ClassStudent classStudents = instance.getClassStudents(1);
    assertNotNull(classStudents);
    assertEquals(1, classStudents.getId());
  }
}
