package com.student.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseDto {
    private Integer courseId;

    private String title;

    private String description;
}
