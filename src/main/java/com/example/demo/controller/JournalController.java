package com.example.demo.controller;

import com.example.demo.data.entity.Journal;
import com.example.demo.data.entity.User;
import com.example.demo.service.JournalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/journal")
@AllArgsConstructor
public class JournalController {

    private final JournalService journalService;


    @GetMapping(value = "/table")
    public String journal(){
        return "journal";
    }

    @PostMapping("/journal/create")
    public ResponseEntity<Journal> createJournal(@RequestBody Journal journal) {
        return ResponseEntity
                .status(200)
                .body(journalService.create(journal));
    }
    @GetMapping("/journal")
    public String JournalsByUser (Model model, User user) {


        model.addAttribute("journals", journalService.getJournalByUserId(user.getId()));
        return "journal";
    }
//    @PostMapping("/journal/getByCourse")
//    public ResponseEntity<Course> getByCourse(@RequestBody String courseName) {
//        return ResponseEntity
//                .status(200)
//                .body(courseService.create(courseName));
//    }

}
