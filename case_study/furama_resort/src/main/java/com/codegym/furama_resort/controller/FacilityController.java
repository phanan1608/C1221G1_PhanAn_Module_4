package com.codegym.furama_resort.controller;

import com.codegym.furama_resort.Dto.CustomerDto;
import com.codegym.furama_resort.Dto.FacilityDto;
import com.codegym.furama_resort.entity.Customer;
import com.codegym.furama_resort.entity.RentType;
import com.codegym.furama_resort.entity.Service;
import com.codegym.furama_resort.entity.ServiceType;
import com.codegym.furama_resort.repository.IFacilityTypeRepository;
import com.codegym.furama_resort.repository.IRentTypeRepository;
import com.codegym.furama_resort.service.IFacilityService;
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
@RequestMapping(value = "/facility")
public class FacilityController {
    @Autowired
    IFacilityService facilityService;

    @Autowired
    IFacilityTypeRepository facilityTypeRepository;

    @Autowired
    IRentTypeRepository rentTypeRepository;

    @ModelAttribute("facilityTypeList")
    public List<ServiceType> getServiceType() {
        return facilityTypeRepository.findAll();
    }

    @ModelAttribute("rentTypeList")
    public List<RentType> getRentType() {
        return rentTypeRepository.findAll();
    }

    @GetMapping("/list")
    public String goToFacilityList(Model model) {
        model.addAttribute("facilityList", facilityService.findAll());
        return "/facility/list2";
    }

    @GetMapping("/create")
    public String goToCreateFacility(Model model) {;
        model.addAttribute("facilityDto", new FacilityDto());
        return "/facility/create";
    }

    @PostMapping("/save")
    public String saveFacility(@ModelAttribute @Validated FacilityDto facilityDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        new FacilityDto().validate(facilityDto,bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return "/facility/create";
        } else {
            Service service = new Service();
            BeanUtils.copyProperties(facilityDto, service);
            this.facilityService.save(service);
            redirectAttributes.addFlashAttribute("msg", "Create Successfully");
            return "redirect:/facility/list";
        }
    }
    @ExceptionHandler(Exception.class)
    public String showToErrorPage() {
        return "/error/error";
    }
}
