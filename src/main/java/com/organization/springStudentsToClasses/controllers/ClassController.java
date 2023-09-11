package com.organization.springStudentsToClasses.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.organization.springStudentsToClasses.models.ClassBase;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/class")
public class ClassController {

  @Autowired
  public ClassController() {
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/")
  public ResponseEntity getAllClasses() {
    return new ResponseEntity(HttpStatus.OK);
  }

  @RequestMapping(method=POST, value="/")
  public ResponseEntity createStudent(@RequestBody ClassBase classBase) {
    return new ResponseEntity(HttpStatus.OK);
  }

  @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
  public ResponseEntity updateStudent(@PathVariable int id, @RequestBody ClassBase classBase) {
    return new ResponseEntity(HttpStatus.OK);
  }

  @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
  public ResponseEntity deleteStudent(@PathVariable int id) {
    return new ResponseEntity(HttpStatus.OK);
  }
}
