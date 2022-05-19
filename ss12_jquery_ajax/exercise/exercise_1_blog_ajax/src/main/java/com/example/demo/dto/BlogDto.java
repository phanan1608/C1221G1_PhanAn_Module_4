package com.example.demo.dto;

import com.example.demo.model.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class BlogDto {
    private Integer id;
    @NotEmpty
    @NotBlank
    private String title;
    @NotEmpty
    @NotBlank
    private String author;
    @NotEmpty
    @NotBlank
    private String content;
    private String createDate;
    private Category category;

    public BlogDto() {
    }

    public BlogDto(@NotEmpty @NotBlank Integer id, @NotEmpty @NotBlank String title, @NotEmpty @NotBlank String author, @NotEmpty @NotBlank String content, String createDate, Category category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.createDate = createDate;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
