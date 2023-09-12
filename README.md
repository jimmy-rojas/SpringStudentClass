# SpringStudentCourse

## Compilation ##
In order to compile run:
- execute the command: 

``` mvn clean install ```

### Execution ###
In order to execute look for the artifact in the target folder
- execute the command: 

``` java -jar target/springStudentCourse-RC-1.1.0.jar ```

### API Testing ###
To test functionality, you can browse into http://localhost:8080/swagger-ui/index.html 

### Additional note ###
This sample project is using mock data which is load ramdomly at start Spring app

---
### Coding Exercise Details ###
Summary:
Create a REST API for a system that assigns students to courses. API will be used by both a UI and programmatically by other systems.

Deliverables:
1. (required) backend code via a GitHub repository.
2. (required) a short write-up around what technologies/frameworks you are/would use in implementing various parts/tiers of this system
4. (optional) API documentation

Timeframe:
The scope of the exercise is somewhat fluid so do not spend more than 2-3 hours on it.

Detailed Requirements:
Models:
Student = { student id, last name, first name }
Course = { code, title, description }
Students can attend an unlimited number of courses. Each Course can have an unlimited number of students

Operations:
Create/Edit/Delete Student
Create/Edit/Delete Course
Browse list of all Student
Browse list of all Courses
View all Students assigned to a Courses
View all Courses assigned to a Student
Search Student/Courses by available fields/associations

Security: None

Error Handling: Does not need to be thorough. Just enough to demonstrate how you would handle various types of errors (business, system)
Persistence: Not part of the evaluation. Feel free to mock it if that’s faster.
