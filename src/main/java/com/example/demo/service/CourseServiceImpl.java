package com.example.demo.service;

import com.example.demo.data.dto.CourseSortDto;
import com.example.demo.data.entity.Course;
import com.example.demo.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserService userService;

    @Override
    public Course create(Course course) {
        Optional<Course> byName = courseRepository.findByName(course.getName());
        if (byName.isEmpty()) {
            return courseRepository.save(course);
        } else {
            return byName.get();
        }
    }


    @Override
    public Course update(Course course) {
        return courseRepository.saveAndFlush(course);
    }

    @Override
    public void delete(Course course) {
        courseRepository.delete(course);
    }

    @Override
    public Course get(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("course not found"));
    }

    @Override
    public Course getByTopic(String topic) {
        return courseRepository.findByTopic(topic).orElseThrow(() -> new IllegalArgumentException("course not found"));
    }

    @Override
    public Course getByTeacher(Long id) {
        return courseRepository.findByTeacher(userService.getId(id)).orElseThrow(() -> new IllegalArgumentException("course not found"));
    }

    @Override
    public List<Course> getSorted(CourseSortDto sortDto) {
        List<Course> sortedCourses = courseRepository.findAll(sortDto.getSortByName() != null ? Sort.by(sortDto.getSortByName().getDirection(), "name") : Sort.unsorted());
        Comparator<Course> comparing = Comparator.comparing(course -> Duration.between(course.getEndDate().toLocalDate(), course.getStartDate().toLocalDate()));
        if (sortDto.getSortByDuration().getDirection().equals(Sort.Direction.DESC))
            comparing.reversed();
        comparing.thenComparingInt(o -> o.getUsers().size());
        if (sortDto.getSortByNumberOfStudents().getDirection().equals(Sort.Direction.DESC))
            comparing.reversed();
        sortedCourses.sort(comparing);
        return sortedCourses;
    }
}
