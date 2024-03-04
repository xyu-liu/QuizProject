package com.example.quizproject.controller;

import com.example.quizproject.domain.User;
import com.example.quizproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String getLogin(HttpServletRequest request, Model model) {
        return "register";
    }

    @PostMapping("")
    public String postLogin(@RequestParam String email,
                            @RequestParam String password,
                            @RequestParam String firstname,
                            @RequestParam String lastname,
                            HttpServletRequest request) {

        userService.createNewUser(email, password, firstname, lastname);
        return "redirect:/login";

    }
}
