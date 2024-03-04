package com.example.quizproject.service;

import com.example.quizproject.dao.CategoryDao;
import com.example.quizproject.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    public List<Category> getAllCategory() {
        return this.categoryDao.getAllCategory();
    }

    public Category getCategoryById(int category_id) {
        return this.getCategoryById(category_id);
    }


}
