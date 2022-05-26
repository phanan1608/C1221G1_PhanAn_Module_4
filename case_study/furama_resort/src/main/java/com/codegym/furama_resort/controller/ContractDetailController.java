package com.codegym.furama_resort.controller;

import com.codegym.furama_resort.Dto.ContractDetailDto;
import com.codegym.furama_resort.Dto.ContractDto;
import com.codegym.furama_resort.entity.Contract;
import com.codegym.furama_resort.entity.ContractDetail;
import com.codegym.furama_resort.repository.IContractRepository;
import com.codegym.furama_resort.service.IContractDetailService;
import com.codegym.furama_resort.service.IContractService;
import com.codegym.furama_resort.service.IServiceAttachService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/contract-detail")
public class ContractDetailController {
    @Autowired
    private IContractService contractService;

    @Autowired
    private IServiceAttachService attachService;

    @Autowired
    private IContractDetailService contractDetailService;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("contractList", contractService.findAll());
        model.addAttribute("attachServiceList", attachService.findAll());
    }

    @GetMapping(value = "/list")
    public String goListContract(@PageableDefault(value = 5) Pageable pageable, Model model) {
        Page<ContractDetail> contractDetailPage = contractDetailService.findAll(pageable);
        model.addAttribute("contractDetailList", contractDetailPage);
        model.addAttribute("contractList", contractService.findAll());
        model.addAttribute("attachServiceList", attachService.findAll());
        return "contract-detail/list";
    }

//    @GetMapping(value = "/create")
//    public String showCreateContract(Model model) {
//        model.addAttribute("contractDetailDto", new ContractDetailDto());
//
//        return "contract-detail/create";
//    }
//
//    @PostMapping(value = "/save")
//    public String saveService(@ModelAttribute @Validated ContractDetailDto contractDetailDto,
//                              BindingResult bindingResult,
//                              RedirectAttributes redirectAttributes,
//                              Model model) {
//        new ContractDetailDto().validate(contractDetailDto,bindingResult);
//        if (bindingResult.hasFieldErrors()) {
//            return "contract-detail/create";
//        } else {
//            ContractDetail contractDetail = new ContractDetail();
//            BeanUtils.copyProperties(contractDetailDto, contractDetail);
//            contractDetailService.save(contractDetail);
//            redirectAttributes.addFlashAttribute("msg", "Create Success");
//            return "redirect:/contract-detail/list";
//        }
//    }

    @ExceptionHandler(Exception.class)
    public String showToErrorPage() {
        return "/error/error";
    }
}
