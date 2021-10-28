package com.unibuc.ex3curs4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/greeting")
    public String hello(@RequestParam(name="name", defaultValue = "world") String name, Model model) {
        model.addAttribute("gigel", name);
        return "greeting";
    }
}
