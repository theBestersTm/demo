package com.example.demo.controller;

import com.example.demo.data.entity.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/registration")
    public String registerUser() {
        return "/registration";
    }
//    @GetMapping(value = "/welcomePage")
//    public String welcomeUser() {
//        return SecurityContextHolder.getContext().getAuthentication().getName();
//    }

    @PostMapping(value = "/registration")
    public ResponseEntity registerUser(User json) {
        return ResponseEntity
                .status(200)
                .body(userService.create(json));
    }

    @GetMapping(value = "/login")
    public String login(){
        return "/login";
//            @RequestParam(value = "error", required = false) String error,
//            @RequestParam(value = "logout", required = false) String logout) {
//
//        ModelAndView model = new ModelAndView();
//        if (error != null) {
//            model.addObject("error", "Invalid username and password!");
//        }
//
//        if (logout != null) {
//            model.addObject("msg", "You've been logged out successfully.");
//        }
//        model.setViewName("login");
//
//        return model;

    }

    @GetMapping(value = "/welcomePage")
    public String personalInform(Model model){

        model.addAttribute("name", SecurityContextHolder.getContext().getAuthentication().getName());
        return "welcomePage";

    }
}
