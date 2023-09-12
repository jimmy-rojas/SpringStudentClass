package com.organization.springStudentCourse.storage;

import com.organization.springStudentCourse.models.CourseDTO;
import com.organization.springStudentCourse.models.CourseStudentsWrapper;
import com.organization.springStudentCourse.models.StudentCoursesWrapper;
import com.organization.springStudentCourse.models.StudentDTO;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class MockDataStorage {

  private Map<Integer, CourseStudentsWrapper> classStudentMap = new HashMap<>();
  private Map<Integer, StudentCoursesWrapper> studentClassMap = new HashMap<>();

  public final AtomicInteger counterStudent = new AtomicInteger();
  public final AtomicInteger counterClass = new AtomicInteger();

  @PostConstruct
  private void init() {
    Map<Integer, StudentDTO> studentMap = new HashMap<>();
    Map<Integer, CourseDTO> courseMap = new HashMap<>();

    for (int i=0; i<5; i++) {
      StudentDTO studentDTO = new StudentDTO(i,"firstName-"+i, "lastName-"+i);
      studentMap.put(i, studentDTO);
      counterStudent.incrementAndGet();

      StudentCoursesWrapper studentCoursesWrapper = new StudentCoursesWrapper(studentDTO.getId(),
          studentDTO.getFirstName(), studentDTO.getLastName(), new HashSet<>());
      studentClassMap.put(i, studentCoursesWrapper);
    }
    for (int i=0; i<3; i++) {
      CourseDTO courseDTO = new CourseDTO(i,"code-"+i, "title-"+i, "description-"+i);
      courseMap.put(i, courseDTO);
      counterClass.incrementAndGet();
      int rand = new Random().nextInt(studentMap.size());
      Set<StudentDTO> studentList = new HashSet<>();
      for (int j=0; j<=rand; j++) {
        studentList.add(studentMap.get(j));
        studentClassMap.get(j).getCourses().add(courseDTO);
      }
      CourseStudentsWrapper courseStudentsWrapper = new CourseStudentsWrapper(courseDTO.getId(), courseDTO.getCode(),
          courseDTO.getTitle(), courseDTO.getDescription(), studentList);
      classStudentMap.put(i, courseStudentsWrapper);
    }
  }

  public Map<Integer, CourseStudentsWrapper> getClassStudentMap() {
    return classStudentMap;
  }

  public Map<Integer, StudentCoursesWrapper> getStudentClassMap() {
    return studentClassMap;
  }

}
