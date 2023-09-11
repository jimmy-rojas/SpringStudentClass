package com.organization.springStudentsToClasses.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassBase;
import com.organization.springStudentsToClasses.models.ClassWithId;
import com.organization.springStudentsToClasses.models.StudentWithId;
import com.organization.springStudentsToClasses.storage.IClassRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClassSaveServiceTest {

  private ClassSaveService instance;
  private IClassRepository classRepository;
  private ClassBase classBase;

  @Before
  public void setUp() {
    classBase = new ClassBase("code", "title", "description");
    classRepository = new IClassRepository() {
      @Override
      public List<ClassWithId> getAllSearch(String code, String title, String description) {
        List<ClassWithId> data = new ArrayList<>();
        data.add(new ClassWithId(1, code, title, description));
        return data;
      }

      @Override
      public List<ClassWithId> getAll() {
        return new ArrayList<>();
      }

      @Override
      public ClassWithId save(ClassBase classBase) {
        return new ClassWithId(1, "code", "title", "description");
      }

      @Override
      public ClassWithId update(int id, ClassBase classBase) throws NotFoundException {
        if (id > 0) {
          return new ClassWithId(id, classBase.getCode(), classBase.getTitle(),
              classBase.getDescription());
        }
        throw new NotFoundException("Not Found");
      }

      @Override
      public void delete(int classId) throws NotFoundException {
        if (classId < 1) {
          throw new NotFoundException("Not Found");
        }
      }
    };
    instance = new ClassSaveService(classRepository);
  }

  @Test
  public void testGetAll() {
    List<ClassWithId> allClasses = instance.getAll();
    assertNotNull(allClasses);
    assertEquals(0, allClasses.size());
  }

  @Test
  public void getAllSearch() throws Exception {
    List<ClassWithId> allClasses = instance.getAllSearch("code", "title", "description");
    assertNotNull(allClasses);
    Assert.assertEquals(1, allClasses.size());
  }

  @Test
  public void testCreate() {
    ClassWithId classSaved = instance.save(classBase);
    assertNotNull(classSaved);
    assertEquals(1, classSaved.getId());
  }

  @Test (expected = NotFoundException.class)
  public void testUpdate_NotFoundException() throws Exception {
    instance.update(0, classBase);
  }

  @Test
  public void testUpdate() throws Exception {
    ClassWithId classUpdated = instance.update(1, classBase);
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