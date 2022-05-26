package com.codegym.furama_resort.Dto;

import com.codegym.furama_resort.common.Validate;
import com.codegym.furama_resort.entity.Contract;
import com.codegym.furama_resort.entity.RentType;
import com.codegym.furama_resort.entity.ServiceType;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

public class FacilityDto implements Validator {
    private Integer serviceId;
    private String serviceCode;
    private String serviceName;
    private String serviceArea;
    private Double serviceCost;
    private String serviceMaxPeople;
    private String standardRoom;
    private String descriptionOtherConvenience;
    private Double poolArea;
    private Integer numberOfFloor;
    private RentType rentType;
    private ServiceType serviceType;
    private List<Contract> contractList;
    private String urlImage;


    public FacilityDto() {
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }


    public Double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public String getServiceMaxPeople() {
        return serviceMaxPeople;
    }

    public void setServiceMaxPeople(String serviceMaxPeople) {
        this.serviceMaxPeople = serviceMaxPeople;
    }

    public String getStandardRoom() {
        return standardRoom;
    }

    public void setStandardRoom(String standardRoom) {
        this.standardRoom = standardRoom;
    }

    public String getDescriptionOtherConvenience() {
        return descriptionOtherConvenience;
    }

    public void setDescriptionOtherConvenience(String descriptionOtherConvenience) {
        this.descriptionOtherConvenience = descriptionOtherConvenience;
    }

    public Double getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(Double poolArea) {
        this.poolArea = poolArea;
    }

    public Integer getNumberOfFloor() {
        return numberOfFloor;
    }

    public void setNumberOfFloor(Integer numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    public RentType getRentType() {
        return rentType;
    }

    public void setRentType(RentType rentType) {
        this.rentType = rentType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        FacilityDto facilityDto = (FacilityDto) target;
        if ("".matches(facilityDto.serviceName)){
            errors.rejectValue("serviceName","blank.error","System error");
        }
        if (facilityDto.serviceCost == null){
            errors.rejectValue("serviceCost","blank.error","System error");
        } else if (facilityDto.serviceCost < 0){
            errors.rejectValue("serviceCost","position-number.error","System error");
        }

        if (facilityDto.serviceMaxPeople.matches("")){
            errors.rejectValue("serviceMaxPeople","blank.error","System error");
        }
         else if (!facilityDto.serviceMaxPeople.matches(Validate.POSITIVE_INTEGERS_REGEX)){
            errors.rejectValue("serviceMaxPeople","position-integer.error","System error");
        }

        if (facilityDto.rentType == null){
            errors.rejectValue("rentType","blank.error","System error");
        }

        if (facilityDto.serviceArea.matches("")){
            errors.rejectValue("serviceArea","blank.error","System error");
        } else if (!facilityDto.serviceArea.matches(Validate.POSITIVE_INTEGERS_REGEX)){
            errors.rejectValue("serviceArea","position-number.error","System error");
        }

        if ("".matches(facilityDto.standardRoom)){
            errors.rejectValue("standardRoom","blank.error","System error");
        }

        if ("".matches(facilityDto.urlImage)){
            errors.rejectValue("urlImage","blank.error","System error");
        }
        if (facilityDto.serviceType == null){
            errors.rejectValue("serviceType","blank.error","System error");
        }

    }
}
