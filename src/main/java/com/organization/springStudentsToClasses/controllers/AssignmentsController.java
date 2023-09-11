package com.organization.springStudentsToClasses.controllers;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.services.AssignmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AssignmentsController exposes all endpoints related to Assignments for Students and Classes
 */
@RestController
@RequestMapping("v1/assignments")
public class AssignmentsController {

  private final AssignmentsService service;

  @Autowired
  public AssignmentsController(AssignmentsService service) {
    this.service = service;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/allStudents")
  public ResponseEntity getAllStudents() {
    return new ResponseEntity(this.service.getAllStudents(), HttpStatus.OK);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/student/{studentId}")
  public ResponseEntity getStudentClasses(@PathVariable int studentId)
      throws NotFoundException {
    return new ResponseEntity(this.service.getStudentClasses(studentId), HttpStatus.OK);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/allClasses")
  public ResponseEntity getAllClasses() {
    return new ResponseEntity(this.service.getAllClasses(), HttpStatus.OK);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/class/{classId}")
  public ResponseEntity getClassStudents(@PathVariable int classId)
      throws NotFoundException {
    return new ResponseEntity(this.service.getClassStudents(classId), HttpStatus.OK);
  }
}
