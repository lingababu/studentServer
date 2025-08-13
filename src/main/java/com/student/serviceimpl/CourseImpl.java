package com.student.serviceimpl;

import com.student.entity.Course;
import com.student.repo.CourseRepo;
import com.student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseImpl implements CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Override
    public boolean addCourse(Course course) {
        Course savedCourse =courseRepo.save(course);
        return savedCourse.getCourseId() != null;
    }
}
