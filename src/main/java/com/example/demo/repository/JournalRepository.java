package com.example.demo.repository;

import com.example.demo.data.entity.Course;
import com.example.demo.data.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JournalRepository extends JpaRepository<Journal, Long> {
    Optional<Journal> findByCourse(Course course);
}
