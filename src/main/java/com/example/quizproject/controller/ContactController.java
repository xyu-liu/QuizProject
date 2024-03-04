package com.example.quizproject.controller;

import com.example.quizproject.domain.Contact;
import com.example.quizproject.service.ContactService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("")
    public String contactPage(HttpServletRequest request, Model model){
        List<Contact> allContacts = this.contactService.getAllContacts();
        model.addAttribute("contacts", allContacts);
        return "contact";
    }



}
