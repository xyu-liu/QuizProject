package com.example.quizproject.service;

import com.example.quizproject.dao.ContactDao;
import com.example.quizproject.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactDao contactDao;

    public List<Contact> getAllContacts() {
        return this.contactDao.getAllContacts();
    }
}
