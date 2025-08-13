package com.student.repo;

import com.student.entity.Student;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

public interface StudentRepo extends JpaRepositoryImplementation<Student, Integer> {

    public Student findByStudentId(Integer id);

    @Query(value = "select s from Student s where s.email like ?1")
    public Student findByEmail(String email);

}
