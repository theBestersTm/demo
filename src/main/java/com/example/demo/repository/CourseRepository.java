package com.example.demo.repository;

import com.example.demo.data.entity.Course;
import com.example.demo.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByTeacher(User user);
    Optional<Course> findByName(String name);
    Optional<Course> findByTopic(String topic);
}
