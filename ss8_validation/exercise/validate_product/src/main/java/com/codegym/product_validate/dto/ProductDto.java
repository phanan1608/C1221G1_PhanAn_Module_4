package com.codegym.product_validate.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class ProductDto {
    private Integer id;


    @NotEmpty(message = "Name {empty.error}")
    @Pattern(regexp = "^[a-zA-Z\\d\\s]*$",message = "Don't enter special character")
    private String name;

    @NotEmpty(message = "Price {empty.error}")
    @Pattern(regexp = "^\\+*\\d+$",message = "Please enter positive integer")
    private String price;

    @NotEmpty(message = "Quantity {empty.error}")
    @Pattern(regexp = "^\\+*\\d+$",message = "Please enter positive integer")
    private String quantity;

    public ProductDto() {
    }

    public ProductDto(Integer id, @NotEmpty(message = "Name {empty.error}") String name, @NotEmpty(message = "Price {empty.error}") String price, @NotEmpty(message = "Quantity {empty.error}") String quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }



}
