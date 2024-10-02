package az.rentall.mvp.util.annotation;

import jakarta.validation.Payload;

public @interface ValidPhoneNumber {
    String message() default "Invalid phone number.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
