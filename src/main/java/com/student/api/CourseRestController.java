package com.student.api;

import com.student.entity.Course;
import com.student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseRestController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@RequestBody Course course){
        boolean status = courseService.addCourse(course);
        if(status){
            return new ResponseEntity<>("Course data saved successfully with course id: "+ course.getCourseId(), HttpStatus.CREATED);
        }else
            return new ResponseEntity<>("Error occurred while saving data.",HttpStatus.BAD_REQUEST);
    }
}
