package com.codegym.furama_resort.controller;

import com.codegym.furama_resort.Dto.CustomerDto;
import com.codegym.furama_resort.entity.Customer;
import com.codegym.furama_resort.entity.CustomerType;
import com.codegym.furama_resort.service.ICustomerService;
import com.codegym.furama_resort.service.ICustomerTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerTypeService customerTypeService;

    @Autowired
    private ICustomerService customerService;

    @ModelAttribute("customerTypeList")
    public List<CustomerType> getCustomerType() {
        return customerTypeService.findAll();
    }

    @GetMapping("/list")
    public String goToCustomerList(Model model, @PageableDefault(value = 5) Pageable pageable,
                                   @RequestParam Optional<String> name,
                                   @RequestParam Optional<String> phone,
                                   @RequestParam Optional<Integer> type) {
        String nameKeyword = name.orElse("");
        String phoneKeyword = phone.orElse("");
        Integer typeKeyword = type.orElse(-1);
        model.addAttribute("customerList", customerService.findAll(nameKeyword, phoneKeyword, typeKeyword, pageable));
        model.addAttribute("name_keyword", name);
        return "/customer/list";
    }

    @GetMapping("/create")
    public String goToCreateCustomer(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        return "/customer/create";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute @Validated CustomerDto customerDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
//        new CustomerDto().validate(customerDto,bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return "/customer/create";
        } else {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerDto, customer);
            this.customerService.save(customer);
            redirectAttributes.addFlashAttribute("msg", "Create Successfully");
            return "redirect:/customer/list";
        }
    }

    @GetMapping("/view/{id}")
    public String viewBook(@PathVariable Integer id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "/customer/view";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        this.customerService.remove(id);
        redirectAttributes.addFlashAttribute("msg", "Delete Successfully");
        return "redirect:/customer/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {
        Customer customer = this.customerService.findById(id);
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);
        model.addAttribute("customerDto", customerDto);
        return "/customer/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute @Validated CustomerDto customerDto, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasFieldErrors()) {
            return "/customer/edit";
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        this.customerService.save(customer);
        redirectAttributes.addFlashAttribute("msg", "Update Successfully");
        return "redirect:/customer/list";

    }
}
