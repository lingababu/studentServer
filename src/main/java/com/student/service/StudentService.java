package com.student.service;

import com.student.dto.StudentDto;
import com.student.entity.Student;
import com.student.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;

public interface StudentService {
    public Student addStudent(Student student);
    public Student retriveStudentDetailsById(Integer id);
    public Student retriveStudentDetailsByEmail(String email);
    public Student updateStudentDetails(Integer studentId, Student student);
    public boolean deleteStudentById(Integer id);
}
