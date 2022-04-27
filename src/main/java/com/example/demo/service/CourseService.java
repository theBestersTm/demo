package com.example.demo.service;

import com.example.demo.data.dto.CourseSortDto;
import com.example.demo.data.entity.Course;

import java.util.List;

public interface CourseService {
    Course create(Course course);
    Course update(Course course);
    void delete(Course course);
    Course get(Long id);
    Course getByTopic(String topic);
    Course getByTeacher(Long id);
    List<Course> getSorted(CourseSortDto sortDto);
}
