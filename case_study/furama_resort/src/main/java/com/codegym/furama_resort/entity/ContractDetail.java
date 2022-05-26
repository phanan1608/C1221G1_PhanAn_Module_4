package com.codegym.furama_resort.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class ContractDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contract_detail_id;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "contract_id",referencedColumnName = "contractId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "attach_service_id",referencedColumnName = "attachServiceId")
    private AttachService attachService;

    public ContractDetail() {
    }



    public ContractDetail(Integer contract_detail_id, Integer quantity, Contract contract, AttachService attachService) {
        this.contract_detail_id = contract_detail_id;
        this.quantity = quantity;
        this.contract = contract;
        this.attachService = attachService;
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

}
