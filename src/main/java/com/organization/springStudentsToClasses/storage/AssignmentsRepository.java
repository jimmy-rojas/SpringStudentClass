package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassStudent;
import com.organization.springStudentsToClasses.models.StudentClass;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("default")
public class AssignmentsRepository extends MockDataStorage implements IAssignmentsRepository {

  @Override
  public ClassStudent getClassStudents(int classId) throws NotFoundException {
    if (!super.classStudentMap.containsKey(classId)) {
      throw new NotFoundException("unable to find Class");
    }
    return super.classStudentMap.get(classId);
  }

  @Override
  public List<ClassStudent> getAllClasses() {
    return new ArrayList<ClassStudent>(super.classStudentMap.values());
  }

  @Override
  public StudentClass getStudentClasses(int studentId) throws NotFoundException {
    if (!super.studentClassMap.containsKey(studentId)) {
      throw new NotFoundException("unable to find Student");
    }
    return super.studentClassMap.get(studentId);
  }

  @Override
  public List<StudentClass> getAllStudents() {
    return new ArrayList<StudentClass>(super.studentClassMap.values());
  }
}
