package com.codegym.furama_resort.Dto;

import com.codegym.furama_resort.common.Validate;
import com.codegym.furama_resort.entity.ContractDetail;
import com.codegym.furama_resort.entity.Customer;
import com.codegym.furama_resort.entity.Employee;
import com.codegym.furama_resort.entity.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ContractDto implements Validator {
    private Integer contractId;
    private String contractStartDate;
    private String contractEndDate;
    private Double contractDeposit;
    private Double contractTotalMoney;
    private List<ContractDetail> contractDetailList;
    private Customer customer;
    private Employee employee;
    private Service service;

    public ContractDto() {
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public String getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(String contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public Double getContractDeposit() {
        return contractDeposit;
    }

    public void setContractDeposit(Double contractDeposit) {
        this.contractDeposit = contractDeposit;
    }

    public Double getContractTotalMoney() {
        return contractTotalMoney;
    }

    public void setContractTotalMoney(Double contractTotalMoney) {
        this.contractTotalMoney = contractTotalMoney;
    }

    public List<ContractDetail> getContractDetailList() {
        return contractDetailList;
    }

    public void setContractDetailList(List<ContractDetail> contractDetailList) {
        this.contractDetailList = contractDetailList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ContractDto contractDto = (ContractDto) target;
        if (contractDto.contractDeposit == null) {
            errors.rejectValue("contractDeposit", "blank.error", "System Error");
        } else if (contractDto.contractDeposit < 0) {
            errors.rejectValue("contractDeposit", "position-number.error", "System Error");
        }

        if (contractDto.contractTotalMoney == null) {
            errors.rejectValue("contractTotalMoney", "blank.error", "System Error");
        } else if (contractDto.contractTotalMoney < 0) {
            errors.rejectValue("contractTotalMoney", "position-number.error", "System Error");
        }

        if (contractDto.employee == null) {
            errors.rejectValue("employee", "blank.error", "System Error");
        }
        if (contractDto.customer == null) {
            errors.rejectValue("customer", "blank.error", "System Error");
        }
        if (contractDto.service == null) {
            errors.rejectValue("service", "blank.error", "System Error");
        }

        if ("".matches(contractDto.contractStartDate)) {
            errors.rejectValue("contractStartDate", "blank.error", "System Error");
        } else if (!contractDto.contractStartDate.matches(Validate.DATE_TIME_REGEX)) {
            errors.rejectValue("contractStartDate", "birthday.error", "System Error");
        } else {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            Date current = new Date();
            try {
                date = fmt.parse(contractDto.contractStartDate);
//                 KIEM TRA NGAY CO TRONG QUA KHU KHONG
                if (date != null && date.compareTo(new Date()) < 0) {
                    errors.rejectValue("contractStartDate", "", "Start date don't in the past");
                }
            } catch (ParseException e) {
                e.printStackTrace();
                errors.rejectValue("contractStartDate", "birthday.error", "System Error");
            }
        }


        if ("".matches(contractDto.contractEndDate)) {
            errors.rejectValue("contractEndDate", "blank.error", "System Error");
        } else if (!contractDto.contractEndDate.matches(Validate.DATE_TIME_REGEX)) {
            errors.rejectValue("contractEndDate", "birthday.error", "System Error");
        } else {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = null;
            Date endDate = null;
            Date current = new Date();
            try {
                endDate = fmt.parse(contractDto.contractEndDate);
                startDate = fmt.parse(contractDto.contractStartDate);
//                 KIEM TRA NGAY CO TRONG QUA KHU KHONG
                if (endDate != null && endDate.compareTo(startDate) < 0) {
                    errors.rejectValue("contractEndDate", "", "End date must be after Start date");
                }
            } catch (ParseException e) {
                e.printStackTrace();
                errors.rejectValue("contractEndDate", "birthday.error", "System Error");

            }
        }
    }
}
