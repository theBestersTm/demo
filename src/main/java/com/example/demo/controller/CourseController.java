package com.example.demo.controller;

import com.example.demo.data.dto.CourseSortDto;
import com.example.demo.data.entity.Course;
import com.example.demo.data.entity.User;
import com.example.demo.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return ResponseEntity
                .status(200)
                .body(courseService.create(course));
    }

    @PatchMapping
    public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
        return ResponseEntity
                .status(200)
                .body(courseService.update(course));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(courseService.get(id));
    }

    @PostMapping("/getByTeacher")
    public ResponseEntity<Course> getCourseByTeacher(@RequestBody User user) {
        Course course = courseService.getByTeacher(user.getId());
        return ResponseEntity
                .status(200)
                .body(course);
    }

    @PostMapping("/getByTopic")
    public ResponseEntity<Course> getCourseByTopic(@RequestBody String topic) {
        Course course = courseService.getByTopic(topic);
        return ResponseEntity
                .status(200)
                .body(course);
    }

    @PostMapping("/getSortedCourseByName")
    public ResponseEntity<List<Course>> getSortedCourse(@RequestBody CourseSortDto courseSortDto) {
        List<Course> course = courseService.getSorted(courseSortDto);
        return ResponseEntity
                .ok(course);
    }
}
