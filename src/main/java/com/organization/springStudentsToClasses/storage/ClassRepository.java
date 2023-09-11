package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassBase;
import com.organization.springStudentsToClasses.models.ClassWithId;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("default")
public class ClassRepository extends MockDataStorage implements IClassRepository {

  @Override
  public List<ClassWithId> getAll() {
    return new ArrayList<ClassWithId>(super.classMap.values());
  }

  @Override
  public ClassWithId save(ClassBase classBase) {
    int newId = super.counterClass.incrementAndGet();
    ClassWithId classWithId = new ClassWithId(newId, classBase.getCode(), classBase.getTitle(),
        classBase.getDescription());
    super.classMap.put(newId, classWithId);
    return classWithId;
  }

  @Override
  public ClassWithId update(int classId, ClassBase classBase) throws NotFoundException {
    if (!super.classMap.containsKey(classId)) {
      throw new NotFoundException("unable to find student");
    }
    ClassWithId classWithId = super.classMap.get(classId);
    classWithId.setCode(classBase.getCode());
    classWithId.setTitle(classBase.getTitle());
    classWithId.setDescription(classBase.getDescription());
    return classWithId;
  }

  @Override
  public void delete(int classId) throws NotFoundException {
    if (!super.classMap.containsKey(classId)) {
      throw new NotFoundException("unable to find Class");
    }
    super.classMap.remove(classId);
  }
}
