package com.codegym.furama_resort.controller;

import com.codegym.furama_resort.Dto.ContractDetailDto;
import com.codegym.furama_resort.Dto.ContractDto;
import com.codegym.furama_resort.entity.Contract;
import com.codegym.furama_resort.entity.ContractDetail;
import com.codegym.furama_resort.service.*;
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
@RequestMapping(value = "/contract")
public class ContractController {

    @Autowired
    private IContractService contractService;

    @Autowired
    private IServiceAttachService attachService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IFacilityService facilityService;

    @Autowired
    private IContractDetailService contractDetailService;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("customerList", customerService.findAll());
        model.addAttribute("employeeList", employeeService.findAll());
        model.addAttribute("facilityList", facilityService.findAll());
    }


    @GetMapping(value = "/list")
    public String goListContract(@PageableDefault(value = 5) Pageable pageable, Model model) {
        Page<Contract> contractPage = contractService.findAll(pageable);
        model.addAttribute("contractList", contractPage);
        return "contract/list";
    }

    @GetMapping(value = "/create")
    public String showCreateContract(Model model) {
        model.addAttribute("contractDto", new ContractDto());

        return "contract/create";
    }

    @PostMapping(value = "/save")
    public String saveService(@ModelAttribute @Validated ContractDto contractDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        new ContractDto().validate(contractDto,bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "contract/create";
        } else {
            Contract contract = new Contract();
            BeanUtils.copyProperties(contractDto, contract);
            contractService.save(contract);
            redirectAttributes.addFlashAttribute("msg", "Create Success");
            return "redirect:/contract/list";
        }
    }

    @GetMapping(value = "/create-contract-detail/{id}")
    public String showCreateContractDetail(Model model, @PathVariable Integer id) {;
        ContractDetailDto contractDetailDto = new ContractDetailDto();
        Contract contract = contractService.findById(id);
        contractDetailDto.setContract(contract);
        model.addAttribute("contractDetailDto", contractDetailDto);
        model.addAttribute("attachServiceList", attachService.findAll());
        return "/contract-detail/create";
    }

    @PostMapping(value = "/saveContractDetail")
    public String saveService(@ModelAttribute @Validated ContractDetailDto contractDetailDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        new ContractDetailDto().validate(contractDetailDto,bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "contract-detail/create";
        } else {
            ContractDetail contractDetail = new ContractDetail();
            BeanUtils.copyProperties(contractDetailDto, contractDetail);
            contractDetailService.save(contractDetail);
            redirectAttributes.addFlashAttribute("msg", "Create Success");
            return "redirect:/contract/list";
        }
    }

    @GetMapping(value = "/total-list")
    public String goListTotal(@PageableDefault(value = 5) Pageable pageable, Model model) {
        Page<ITotalMoney> totalMoneyPage = contractService.findAllByTotal(pageable);
        model.addAttribute("totalList", totalMoneyPage);
        return "contract/total";
    }

    @ExceptionHandler(Exception.class)
    public String showToErrorPage() {
        return "/error/error";
    }
}
