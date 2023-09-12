package com.organization.springStudentCourse.storage;

import com.organization.springStudentCourse.exceptions.NotFoundException;
import com.organization.springStudentCourse.models.CourseStudentsWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("default")
public class CourseRepository implements ICourseRepository {

  @Autowired
  private MockDataStorage storage;

  @Override
  public List<CourseStudentsWrapper> getAll() {
    return new ArrayList<>(storage.getClassStudentMap().values());
  }

  @Override
  public CourseStudentsWrapper getById(int id) throws NotFoundException {
    if (!storage.getClassStudentMap().containsKey(id)) {
      throw new NotFoundException("Unable to find course with id: " + id);
    }
    return storage.getClassStudentMap().get(id);
  }

  @Override
  public CourseStudentsWrapper save(CourseStudentsWrapper classBase) {
    int newId = storage.counterClass.incrementAndGet();
    classBase.setId(newId);
    storage.getClassStudentMap().put(newId, classBase);
    return classBase;
  }

  @Override
  public CourseStudentsWrapper update(CourseStudentsWrapper classBase) throws NotFoundException {
    storage.getClassStudentMap().put(classBase.getId(), classBase);
    return classBase;
  }

  @Override
  public void delete(int classId) throws NotFoundException {
    CourseStudentsWrapper classData = getById(classId);
    storage.getClassStudentMap().remove(classData.getId());
  }

  @Override
  public List<CourseStudentsWrapper> getAllSearch(String code, String title, String description) {
    return storage.getClassStudentMap().values()
        .stream()
        .filter((classWithId) ->
            classWithId.getCode().equalsIgnoreCase(code)
                || classWithId.getTitle().equalsIgnoreCase(title)
                || classWithId.getDescription().equalsIgnoreCase(description)
        ).collect(Collectors.toList());
  }
}
