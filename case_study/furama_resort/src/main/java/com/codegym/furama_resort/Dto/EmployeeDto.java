package com.codegym.furama_resort.Dto;

import com.codegym.furama_resort.common.Validate;
import com.codegym.furama_resort.entity.*;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

public class EmployeeDto implements Validator {
    private String employeeId;
    private String employeeName;
    private String employeeBirthday;
    private String employeeIdCard;
    private Double employeeSalary;
    private String employeePhone;
    private String employeeEmail;
    private String employeeAddress;
    private Position position;
    private EducationDegree educationDegree;
    private Division division;
    private String urlImage;

    public EmployeeDto() {
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeBirthday() {
        return employeeBirthday;
    }

    public void setEmployeeBirthday(String employeeBirthday) {
        this.employeeBirthday = employeeBirthday;
    }

    public String getEmployeeIdCard() {
        return employeeIdCard;
    }

    public void setEmployeeIdCard(String employeeIdCard) {
        this.employeeIdCard = employeeIdCard;
    }

    public Double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(Double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public EducationDegree getEducationDegree() {
        return educationDegree;
    }

    public void setEducationDegree(EducationDegree educationDegree) {
        this.educationDegree = educationDegree;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

        EmployeeDto employeeDto = (EmployeeDto) target;
        if ("".matches(employeeDto.employeeName)) {
            errors.rejectValue("employeeName", "blank.error", "System Error");
        } else if (!employeeDto.employeeName.matches(Validate.FULL_NAME_REGEX)) {
            errors.rejectValue("employeeName", "name.error", "System Error");
        }


        if (!employeeDto.employeePhone.matches(Validate.TELEPHONE_NUMBER_REGEX)) {
            errors.rejectValue("employeePhone", "phone.error", "System Error");
        }

        if (!employeeDto.employeeIdCard.matches(Validate.ID_CARD_REGEX)) {
            errors.rejectValue("employeeIdCard", "id_card.error", "System Error");
        }

        if (!employeeDto.employeeEmail.matches(Validate.EMAIL_ADDRESS_REGEX)) {
            errors.rejectValue("employeeEmail", "email.error", "System Error");
        }

        if (employeeDto.employeeSalary == null) {
            errors.rejectValue("employeeSalary", "blank.error", "System Error");
        } else if (employeeDto.employeeSalary < 0) {
            errors.rejectValue("employeeSalary", "position-number.error", "System Error");
        }

        if ("".matches(employeeDto.employeeAddress)) {
            errors.rejectValue("employeeAddress", "blank.error", "System Error");
        }

        if ("".matches(employeeDto.urlImage)) {
            errors.rejectValue("urlImage", "blank.error", "System Error");
        }
        if (employeeDto.position == null) {
            errors.rejectValue("position", "blank.error", "System Error");
        }
        if (employeeDto.educationDegree == null) {
            errors.rejectValue("educationDegree", "blank.error", "System Error");
        }
        if (employeeDto.division == null) {
            errors.rejectValue("division", "blank.error", "System Error");
        }


        if ("".matches(employeeDto.employeeBirthday)) {
            errors.rejectValue("employeeBirthday", "blank.error", "System Error");
        } else if (!employeeDto.employeeBirthday.matches(Validate.DATE_TIME_REGEX)) {
            errors.rejectValue("employeeBirthday", "birthday.error", "System Error");
        } else {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date birthdayDate = null;
            Date current = new Date();
            fmt.setLenient(false);
            try {
                birthdayDate = fmt.parse(employeeDto.employeeBirthday);
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
                    errors.rejectValue("employeeBirthday", "", "MUST BE > 18 YEAR OLD");
                } else if (years > 100) {
                    errors.rejectValue("employeeBirthday","","MUST BE < 100 YEAR OLD");
                }
            } catch (ParseException e) {
                e.printStackTrace();
                errors.rejectValue("employeeBirthday", "birthday.error", "System Error");
            }
        }
    }
}

