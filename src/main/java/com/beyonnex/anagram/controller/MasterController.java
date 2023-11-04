package com.beyonnex.anagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MasterController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Welcome to the Spring Boot Application!");
        return "index";
    }
}
