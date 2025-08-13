package com.student.repo;

import com.student.entity.Course;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface CourseRepo extends JpaRepositoryImplementation<Course, Integer> {
}
