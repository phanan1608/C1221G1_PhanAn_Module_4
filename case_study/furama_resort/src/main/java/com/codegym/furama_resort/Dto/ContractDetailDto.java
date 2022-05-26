package com.codegym.furama_resort.Dto;

import com.codegym.furama_resort.entity.AttachService;
import com.codegym.furama_resort.entity.Contract;
import com.codegym.furama_resort.entity.ContractDetail;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ContractDetailDto implements Validator {
    private Integer contract_detail_id;
    private Integer quantity;
    private Contract contract;
    private AttachService attachService;

    public ContractDetailDto() {
    }

    public Integer getContract_detail_id() {
        return contract_detail_id;
    }

    public void setContract_detail_id(Integer contract_detail_id) {
        this.contract_detail_id = contract_detail_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public AttachService getAttachService() {
        return attachService;
    }

    public void setAttachService(AttachService attachService) {
        this.attachService = attachService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ContractDetailDto contractDetailDto = (ContractDetailDto) target;
        if (contractDetailDto.contract==null){
            errors.rejectValue("contract","blank.error","System error");
        }
        if (contractDetailDto.attachService==null){
            errors.rejectValue("attachService","blank.error","System error");
        }
        if (contractDetailDto.quantity == null){
            errors.rejectValue("quantity","blank.error","System error");
        } else if (contractDetailDto.quantity < 0){
            errors.rejectValue("quantity","position-number.error","System error");
        }
    }
}
