package az.rentall.mvp.util.annotation;

import az.rentall.mvp.util.validation.PhoneNumberValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;

@Documented
@Constraint(validatedBy = PhoneNumberValidation.class)
public @interface ValidPhoneNumber {
    String message() default "Invalid phone number.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
