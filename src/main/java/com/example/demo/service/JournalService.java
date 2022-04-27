package com.example.demo.service;

import com.example.demo.data.dto.CourseSortDto;
import com.example.demo.data.entity.Course;
import com.example.demo.data.entity.Journal;

import java.util.List;

public interface JournalService {
    Journal create(Journal journal);
    Journal update(Journal journal);
    void delete(Journal journal);
    List <Journal> getJournalByUserId(Long id);
}
