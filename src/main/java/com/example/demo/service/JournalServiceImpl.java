package com.example.demo.service;

import com.example.demo.data.entity.Journal;
import com.example.demo.repository.JournalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JournalServiceImpl implements JournalService {

    private final JournalRepository journalRepository;

    @Override
    public Journal create(Journal journal) {
        Optional<Journal> byName = journalRepository.findByCourse(journal.getCourse());
        if (byName.isEmpty()) {
            return journalRepository.save(journal);
        } else {
            return byName.get();
        }
    }

    @Override
    public Journal update(Journal journal) {
        return null;
    }

    @Override
    public void delete(Journal journal) {

    }

    @Override
    public List<Journal> getJournalByUserId(Long id) {


        List<Journal> journals = new ArrayList<>();
        journals.add(journalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("user not found")));
        return journals;
    }
}
