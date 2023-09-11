package com.organization.springStudentCourse.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.organization.springStudentCourse.exceptions.InvalidOperationException;
import com.organization.springStudentCourse.exceptions.NotFoundException;
import com.organization.springStudentCourse.models.CourseData;
import com.organization.springStudentCourse.services.CourseSaveService;
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
@RequestMapping("v1/courses")
public class CourseController {

  private final CourseSaveService service;

  @Autowired
  public CourseController(CourseSaveService service) {
    this.service = service;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/")
  public ResponseEntity getAllCourses() {
    return new ResponseEntity(this.service.getAll(), HttpStatus.OK);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{courseId}/students")
  public ResponseEntity getCourseStudents(@PathVariable int courseId)
      throws NotFoundException {
    return new ResponseEntity(this.service.getById(courseId), HttpStatus.OK);
  }

  @RequestMapping(method=POST, value="/")
  public ResponseEntity createCourse(@RequestBody CourseData courseData) {
    return new ResponseEntity(this.service.save(courseData), HttpStatus.OK);
  }

  @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/")
  public ResponseEntity updateCourse(@RequestBody CourseData courseData)
      throws NotFoundException {
    return new ResponseEntity(this.service.update(courseData), HttpStatus.OK);
  }

  @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
  public ResponseEntity deleteCourse(@PathVariable int id)
      throws NotFoundException, InvalidOperationException {
    this.service.delete(id);
    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/search")
  public ResponseEntity getAllCoursesSearch(
      @RequestParam("code") String code,
      @RequestParam("title") String title,
      @RequestParam("description") String description) {
    return new ResponseEntity(this.service.getAllSearch(code, title, description), HttpStatus.OK);
  }
}
