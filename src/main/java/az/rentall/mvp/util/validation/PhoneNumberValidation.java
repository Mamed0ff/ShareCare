package az.rentall.mvp.util.validation;

import az.rentall.mvp.util.annotation.ValidPhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidation implements ConstraintValidator <ValidPhoneNumber,String> {
    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String number, ConstraintValidatorContext constraintValidatorContext) {
        if(number==null || number.isBlank()){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Phone number cannot be blank").addConstraintViolation();
            return false;
        }
        if(!number.matches("^\\+994\\d{9}$")){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Phone number must be in the format +994XXXXXXXXX").addConstraintViolation();
            return false;
        }

        return true;
    }
}
