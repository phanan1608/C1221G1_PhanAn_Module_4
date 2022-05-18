package com.example.demo.service;

import com.example.demo.model.Blog;
import com.example.demo.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findById(Integer id);
}
