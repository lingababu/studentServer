package com.student.api;

import com.student.dto.StudentDto;
import com.student.entity.Student;
import com.student.exception.EmailNotFound;
import com.student.exception.StudentNotFoundException;
import com.student.exception.StudentNotSaved;
import com.student.service.StudentService;
import io.swagger.v3.oas.annotations.OpenAPI31;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/student")
@Tag(name = "Student", description = "API documentation")
public class StudentRestController {

    @Autowired
    private StudentService service;

    @PostMapping(value = "/add", consumes = "application/xml", produces = "application/json")
    @Operation(summary = "Add Student", description = "Adding Student details.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Student data saved successfully."),
            @ApiResponse(responseCode = "400", description = "Error occurred while saving data.")
    })
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        Student data = service.addStudent(student);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } else {
            throw new StudentNotSaved("Error occurred while saving student details.");
        }
    }

    @GetMapping(value = "/getStudentBy/{id}",produces = "application/json")
    @Operation(summary = "get student details based on id.", description = "Fetching student details by ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Fetched student details successfully."),
            @ApiResponse(responseCode = "400", description = "Student details not found.")})
    public ResponseEntity<Student> getStudentById(@Parameter(description = "to get student details based on id, need to enter student id here.") @PathVariable Integer id) {

        Student isStudentExist = service.retriveStudentDetailsById(id);

        if (isStudentExist != null) {
            return new ResponseEntity<>(isStudentExist, HttpStatus.OK);
        } else
            throw new StudentNotFoundException("student with id: " + id + " not found.");
    }

    @GetMapping(value = "/getStudentByEmail/", produces = "application/json")
    @Operation(summary = "get student by Email.", description = "Fetching student details by Email.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Fetched student details by email successfully."),
            @ApiResponse(responseCode = "400", description = "Student details not found with email.")})
    public ResponseEntity<Student> getStudentByEmail(@Parameter(description = "Enter email to fetch the student details.") @RequestParam String email) {

        Student studentDetails = service.retriveStudentDetailsByEmail(email);

        if (studentDetails != null) {
            return new ResponseEntity<>(studentDetails, HttpStatus.OK);
        }
        throw new EmailNotFound(email + " doesn't exist");
    }

    @PutMapping(value = "/update/{studentId}", produces = "application/json", consumes = "application/json")
    @Operation(summary = "updating student details.", description = "If student present then only this operation get successfully.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "student details updated successfully."),
            @ApiResponse(responseCode = "400", description = "Error occurred while updating student details.")})
    public ResponseEntity<Student> updateStudent(@Parameter(description = "enter student id to update particular student details.") @PathVariable Integer studentId, @RequestBody Student student) {
        Student updatedDetails = service.updateStudentDetails(studentId, student);

        if (updatedDetails != null) {
            return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
        } else
            throw new StudentNotFoundException("Student with id " + studentId + " not present.");
    }

    ;

    @DeleteMapping("/deleteById")
    @Operation(summary = "deleting student by id.", description = "if student present, then only this operation successfully")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Student deleted successfully."),
            @ApiResponse(responseCode = "400", description = "student details not found.")})
    public ResponseEntity<String> deleteById(@Parameter(description = "enter id to delete the student details.") @RequestParam Integer id) {

        boolean flag = service.deleteStudentById(id);

        if (flag) {
            return new ResponseEntity<String>("Student with Id: " + id + " deleted successfully.", HttpStatus.OK);
        }
        throw new StudentNotFoundException("Student with Id: " + id + " not exists");
    }
}
