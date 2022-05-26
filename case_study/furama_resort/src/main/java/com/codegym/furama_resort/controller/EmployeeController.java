package com.codegym.furama_resort.controller;

import com.codegym.furama_resort.Dto.CustomerDto;
import com.codegym.furama_resort.Dto.EmployeeDto;
import com.codegym.furama_resort.entity.Customer;
import com.codegym.furama_resort.entity.CustomerType;
import com.codegym.furama_resort.entity.Employee;
import com.codegym.furama_resort.service.IDivisionService;
import com.codegym.furama_resort.service.IEducationDegreeService;
import com.codegym.furama_resort.service.IEmployeeService;
import com.codegym.furama_resort.service.IPositionService;
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
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IPositionService positionService;

    @Autowired
    IEducationDegreeService educationDegreeService;

    @Autowired
    IDivisionService divisionService;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("positionList", positionService.findAll());
        model.addAttribute("educationDegreeList", educationDegreeService.findAll());
        model.addAttribute("divisionList", divisionService.findAll());
    }

    @GetMapping("/list")
    public String goToEmployeeList(Model model, @PageableDefault(value = 5) Pageable pageable,
                                   @RequestParam Optional<String> name) {
        String nameKeyword = name.orElse("");
        model.addAttribute("employeeList", employeeService.findAll(nameKeyword, pageable));
        model.addAttribute("name_keyword", nameKeyword);
        return "/employee/list";
    }

    @GetMapping("/create")
    public String goToCreateEmployee(Model model) {
        model.addAttribute("employeeDto", new EmployeeDto());
        return "/employee/create";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute @Validated EmployeeDto employeeDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        new EmployeeDto().validate(employeeDto,bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return "/employee/create";
        } else {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDto, employee);
            this.employeeService.save(employee);
            redirectAttributes.addFlashAttribute("msg", "Create Successfully");
            return "redirect:/employee/list";
        }
    }

    @GetMapping("/view/{id}")
    public String viewBook(@PathVariable String id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "/employee/view";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
        this.employeeService.remove(id);
        redirectAttributes.addFlashAttribute("msg", "Delete Successfully");
        return "redirect:/employee/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable String id) {
        Employee employee = this.employeeService.findById(id);
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee, employeeDto);
        model.addAttribute("employeeDto", employeeDto);
        return "/employee/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute @Validated EmployeeDto employeeDto, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasFieldErrors()) {
            return "/employee/edit";
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        this.employeeService.save(employee);
        redirectAttributes.addFlashAttribute("msg", "Update Successfully");
        return "redirect:/employee/list";

    }
    @ExceptionHandler(Exception.class)
    public String showToErrorPage() {
        return "/error/error";
    }
}
