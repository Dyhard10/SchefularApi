package com.example.demo.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatePhone implements ConstraintValidator<ContactNumberConstraint,String> {
    @Override
    public void initialize(ContactNumberConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
        try {
            return contactField != null && contactField.matches("[0-9]+")
                    && (contactField.length() > 8) && (contactField.length() == 12);

        }catch (Exception e){
            System.out.println("INVALID PHONE NUMBER");
            return false;
        }


    }
}
