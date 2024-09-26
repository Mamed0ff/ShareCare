package az.rentall.mvp.util.annotation;

import az.rentall.mvp.util.validation.ImagesValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import lombok.Data;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ImagesValidation.class)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidImages {
    String message() default "Invalid image(s).";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    long minSize() default 1024;
    long maxSize() default 5 * 1024 * 1024;
    int minCount() default 1;
    int maxCount() default 10;
    String[] allowedFormats() default {"images/jpeg", "images/png", "images/jpg"};
}
