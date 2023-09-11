package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.models.FullClassData;
import java.util.List;

public interface IClassRepository extends IRepository<FullClassData> {

  List<FullClassData> getAllSearch(String code, String title, String description);
}
