package com.organization.springStudentCourse.storage;

import com.organization.springStudentCourse.models.CourseData;
import com.organization.springStudentCourse.models.FullCourseData;
import com.organization.springStudentCourse.models.FullStudentData;
import com.organization.springStudentCourse.models.StudentData;
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

  private Map<Integer, FullCourseData> classStudentMap = new HashMap<>();
  private Map<Integer, FullStudentData> studentClassMap = new HashMap<>();

  public final AtomicInteger counterStudent = new AtomicInteger();
  public final AtomicInteger counterClass = new AtomicInteger();

  @PostConstruct
  private void init() {
    Map<Integer, StudentData> studentMap = new HashMap<>();
    Map<Integer, CourseData> courseMap = new HashMap<>();

    for (int i=0; i<5; i++) {
      StudentData studentData = new StudentData(i,"firstName-"+i, "lastName-"+i);
      studentMap.put(i, studentData);
      counterStudent.incrementAndGet();

      FullStudentData studentClass = new FullStudentData(studentData.getId(),
          studentData.getFirstName(), studentData.getLastName(), new HashSet<>());
      studentClassMap.put(i, studentClass);
    }
    for (int i=0; i<3; i++) {
      CourseData classData = new CourseData(i,"code-"+i, "title-"+i, "description-"+i);
      courseMap.put(i, classData);
      counterClass.incrementAndGet();
      int rand = new Random().nextInt(studentMap.size());
      Set<StudentData> studentList = new HashSet<>();
      for (int j=0; j<=rand; j++) {
        studentList.add(studentMap.get(j));
        studentClassMap.get(j).getCourses().add(classData);
      }
      FullCourseData classStudent = new FullCourseData(classData.getId(), classData.getCode(),
          classData.getTitle(), classData.getDescription(), studentList);
      classStudentMap.put(i, classStudent);
    }
  }

  public Map<Integer, FullCourseData> getClassStudentMap() {
    return classStudentMap;
  }

  public Map<Integer, FullStudentData> getStudentClassMap() {
    return studentClassMap;
  }

}
