package com.student.serviceimpl;

import com.student.dto.StudentDto;
import com.student.entity.Student;
import com.student.exception.StudentNotFoundException;
import com.student.repo.StudentRepo;
import com.student.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public Student addStudent(Student student) {
        Student savedData = studentRepo.save(student);
        boolean status =  savedData.getStudentId() != null;
        if(status){
            return  savedData;
        }else
            return null;
    }

    @Override
    public Student retriveStudentDetailsById(Integer id) {

        Optional<Student> isStudentExist = studentRepo.findById(id);

        if (isStudentExist.isPresent()) {

            Student studentData = isStudentExist.get();

            return studentRepo.findByStudentId(studentData.getStudentId());
        }

        return null;
    }

    public Student retriveStudentDetailsByEmail(String email) {

        Student studentDetails = studentRepo.findByEmail(email);

        if (studentDetails != null) {
            return studentDetails;
        }
        return null;
    }

    public Student updateStudentDetails(Integer studentId, Student student) {
        Optional<Student> isStudentPresent = studentRepo.findById(studentId);
        if (isStudentPresent.isPresent()) {
            Student studentDetails = isStudentPresent.get();
            studentDetails.setEmail(student.getEmail());
            studentDetails.setName(student.getName());
            studentRepo.save(studentDetails);
            return studentDetails;
        } else
            return null;
    }

    public boolean deleteStudentById(Integer id) {

        Optional<Student> studentDetails = studentRepo.findById(id);

        if (studentDetails.isPresent()) {
            studentRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
