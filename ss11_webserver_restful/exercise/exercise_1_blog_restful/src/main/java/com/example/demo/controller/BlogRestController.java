package com.example.demo.controller;

import com.example.demo.model.Blog;
import com.example.demo.model.Category;
import com.example.demo.service.IBlogService;
import com.example.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/blogRest")
@RestController
public class BlogRestController {
    @Autowired
    IBlogService blogService;

    @Autowired
    ICategoryService categoryService;

    @GetMapping("/list-blog")
    public ResponseEntity<Page<Blog>> getBlog(@PageableDefault(value = 2)Pageable pageable,
                                              @RequestParam Optional<String> keyword){
        String key = keyword.orElse("");
        Page<Blog> blogPage = this.blogService.findAll(key, pageable);
        if (!blogPage.hasContent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogPage,HttpStatus.OK);
    }

    @GetMapping("/list-category")
    public ResponseEntity<List<Category>> getCategory(){
        List<Category> categoryPage = this.categoryService.findAll();
        if (categoryPage.size()==0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryPage,HttpStatus.OK);
    }
    @GetMapping("/list-category/{id}")
    public ResponseEntity<List<Blog>> getBlogInCategory(@PathVariable Integer id){
        List<Blog> blogPage = this.categoryService.findById(id).getBlogList();
        if (blogPage.size()==0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogPage,HttpStatus.OK);
    }
    @GetMapping("/view/{id}")
    public ResponseEntity<Blog> getViewBlog(@PathVariable Integer id){
        Blog blog = this.blogService.findById(id);
        if (blog==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blog,HttpStatus.NOT_FOUND);
    }
}
