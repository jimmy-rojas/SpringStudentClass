package com.organization.springStudentCourse.storage;

import com.organization.springStudentCourse.exceptions.NotFoundException;
import com.organization.springStudentCourse.models.FullCourseData;
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
  public List<FullCourseData> getAll() {
    return new ArrayList<>(storage.getClassStudentMap().values());
  }

  @Override
  public FullCourseData getById(int id) throws NotFoundException {
    if (!storage.getClassStudentMap().containsKey(id)) {
      throw new NotFoundException("unable to find student");
    }
    return storage.getClassStudentMap().get(id);
  }

  @Override
  public FullCourseData save(FullCourseData classBase) {
    int newId = storage.counterClass.incrementAndGet();
    classBase.setId(newId);
    storage.getClassStudentMap().put(newId, classBase);
    return classBase;
  }

  @Override
  public FullCourseData update(FullCourseData classBase) throws NotFoundException {
    storage.getClassStudentMap().put(classBase.getId(), classBase);
    return classBase;
  }

  @Override
  public void delete(int classId) throws NotFoundException {
    FullCourseData classData = getById(classId);
    storage.getClassStudentMap().remove(classData.getId());
  }

  @Override
  public List<FullCourseData> getAllSearch(String code, String title, String description) {
    return storage.getClassStudentMap().values()
        .stream()
        .filter((classWithId) ->
            classWithId.getCode().equalsIgnoreCase(code)
                || classWithId.getTitle().equalsIgnoreCase(title)
                || classWithId.getDescription().equalsIgnoreCase(description)
        ).collect(Collectors.toList());
  }
}
