package az.rentall.mvp.util.validation;

import az.rentall.mvp.util.annotation.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordValidation implements ConstraintValidator <ValidPassword, String>{
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=?!.,]).{8,64}$");

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if(password == null || password.isEmpty()){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Password cannot be empty or null").addConstraintViolation();
            return false;
        }
        if(password.length() < 8 || password.length() > 64){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Password must be between 8 and 64 characters").addConstraintViolation();
            return false;
        }
        if(!PASSWORD_PATTERN.matcher(password).matches()){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Password must contain at least one digit, one lowercase letter, one uppercase letter, and one special character").addConstraintViolation();
            return false;
        }
        return true;
    }
}
