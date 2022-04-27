package com.example.demo.data.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class CourseSortDto {
    private SortedField sortByName;
    private SortedField sortByDuration;
    private SortedField sortByNumberOfStudents;

    @Data
    public static class SortedField {
        private Sort.Direction direction;
    }
}
