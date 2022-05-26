package com.codegym.furama_resort.Dto;

import com.codegym.furama_resort.common.Validate;
import com.codegym.furama_resort.entity.Contract;
import com.codegym.furama_resort.entity.CustomerType;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

public class CustomerDto implements Validator {
    private String customerId;
    private String customerCode;
    private String customerName;
    private String customerBirthday;
    private Integer customerGender;
    private String customerIdCard;
    private String customerPhone;
    private String customerEmail;
    private String customerAddress;
    private CustomerType customerType;
    private String urlImage;

    public CustomerDto() {
    }


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerBirthday() {
        return customerBirthday;
    }

    public void setCustomerBirthday(String customerBirthday) {
        this.customerBirthday = customerBirthday;
    }

    public Integer getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(Integer customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerIdCard() {
        return customerIdCard;
    }

    public void setCustomerIdCard(String customerIdCard) {
        this.customerIdCard = customerIdCard;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
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
        CustomerDto customerDto = (CustomerDto) target;
        if ("".matches(customerDto.customerName)) {
            errors.rejectValue("customerName", "blank.error", "System Error");
        } else if (!customerDto.customerName.matches(Validate.FULL_NAME_REGEX)) {
            errors.rejectValue("customerName", "name.error", "System Error");
        }


        if (customerDto.customerGender == null) {
            errors.rejectValue("customerGender", "blank.error", "System Error");
        }

        if (!customerDto.customerPhone.matches(Validate.TELEPHONE_NUMBER_REGEX)) {
            errors.rejectValue("customerPhone", "phone.error", "System Error");
        }

        if (!customerDto.customerIdCard.matches(Validate.ID_CARD_REGEX)) {
            errors.rejectValue("customerIdCard", "id_card.error", "System Error");
        }

        if (!customerDto.customerEmail.matches(Validate.EMAIL_ADDRESS_REGEX)) {
            errors.rejectValue("customerEmail", "email.error", "System Error");
        }

        if ("".matches(customerDto.customerAddress)) {
            errors.rejectValue("customerAddress", "blank.error", "System Error");
        }
        if (customerDto.customerType == null) {
            errors.rejectValue("customerType", "blank.error", "System Error");
        }
        if ("".matches(customerDto.urlImage)) {
            errors.rejectValue("urlImage", "blank.error", "System Error");
        }

        if ("".matches(customerDto.customerBirthday)) {
            errors.rejectValue("customerBirthday", "blank.error", "System Error");
        } else if (!customerDto.customerBirthday.matches(Validate.DATE_TIME_REGEX)) {
            errors.rejectValue("customerBirthday", "birthday.error", "System Error");
        } else {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date birthdayDate = null;
            Date current = new Date();
            try {
                birthdayDate = fmt.parse(customerDto.customerBirthday);
                // KIEM TRA NGAY CO TRONG QUA KHU KHONG
//                if (birthdayDate != null && birthdayDate.compareTo(new Date()) > 0) {
//                    errors.rejectValue("customerBirthday", "","Birthday must in the past");
//                }
                // KIEM TRA TUOI > 18
                OffsetDateTime startOdt = birthdayDate.toInstant().atOffset(ZoneOffset.UTC);
                OffsetDateTime endOdt = current.toInstant().atOffset(ZoneOffset.UTC);
                int years = Period.between(startOdt.toLocalDate(), endOdt.toLocalDate()).getYears();
                System.out.println(years);
                if (years < 18) {
                    errors.reject("customerBirthday","MUST BE > 18 YEAR OLD");
                } else if (years > 100) {
                    errors.reject("customerBirthday","MUST BE < 100 YEAR OLD");
                }
            } catch (ParseException e) {
                e.printStackTrace();
                errors.rejectValue("customerBirthday", "birthday.error", "System Error");
            }
        }
    }
}
