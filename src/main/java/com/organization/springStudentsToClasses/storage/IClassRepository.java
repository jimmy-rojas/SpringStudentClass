package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.models.ClassBase;
import com.organization.springStudentsToClasses.models.ClassWithId;
import java.util.List;

public interface IClassRepository extends IRepository<ClassBase, ClassWithId> {

  List<ClassWithId> getAllSearch(String code, String title, String description);
}
