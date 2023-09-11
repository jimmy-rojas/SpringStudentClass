package com.organization.springStudentsToClasses.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.organization.springStudentsToClasses.exceptions.InvalidOperationException;
import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassData;
import com.organization.springStudentsToClasses.services.ClassSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/class")
public class ClassController {

  private final ClassSaveService service;

  @Autowired
  public ClassController(ClassSaveService service) {
    this.service = service;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/")
  public ResponseEntity getAllClasses() {
    return new ResponseEntity(this.service.getAll(), HttpStatus.OK);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{classId}")
  public ResponseEntity getClassStudents(@PathVariable int classId)
      throws NotFoundException {
    return new ResponseEntity(this.service.getById(classId), HttpStatus.OK);
  }

  @RequestMapping(method=POST, value="/")
  public ResponseEntity createClass(@RequestBody ClassData classData) {
    return new ResponseEntity(this.service.save(classData), HttpStatus.OK);
  }

  @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/")
  public ResponseEntity updateClass(@RequestBody ClassData classData)
      throws NotFoundException {
    return new ResponseEntity(this.service.update(classData), HttpStatus.OK);
  }

  @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
  public ResponseEntity deleteClass(@PathVariable int id)
      throws NotFoundException, InvalidOperationException {
    this.service.delete(id);
    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/search")
  public ResponseEntity getAllClassesSearch(
      @RequestParam("code") String code,
      @RequestParam("title") String title,
      @RequestParam("description") String description) {
    return new ResponseEntity(this.service.getAllSearch(code, title, description), HttpStatus.OK);
  }
}
