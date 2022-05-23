package com.codegym.furama_resort.controller;

import com.codegym.furama_resort.service.ICustomerService;
import com.codegym.furama_resort.service.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    ICustomerTypeService customerTypeService;

    @Autowired
    ICustomerService customerService;

    @GetMapping("/list")
    public String goToHome(Model model, @PageableDefault(value = 5) Pageable pageable, @RequestParam Optional<String> keyword){
        String name = keyword.orElse("");
        model.addAttribute("customerTypeList",customerTypeService.findAll());
        model.addAttribute("customerList",customerService.findAll(name,pageable));
        model.addAttribute("name_keyword",name);
        return "";
    }
}
