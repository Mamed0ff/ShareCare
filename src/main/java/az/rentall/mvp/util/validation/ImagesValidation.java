package az.rentall.mvp.util.validation;

import az.rentall.mvp.util.annotation.ValidImages;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImagesValidation implements ConstraintValidator<ValidImages, List<MultipartFile>> {

    private long minSize;
    private long maxSize;
    private int minCount;
    private int maxCount;
    private Set<String> allowedFormats;



    @Override
    public void initialize(ValidImages constraintAnnotation) {
        this.minSize = constraintAnnotation.minSize();
        this.maxSize = constraintAnnotation.maxSize();
        this.minCount=constraintAnnotation.minCount();
        this.maxCount=constraintAnnotation.maxCount();
        this.allowedFormats=new HashSet<>(Arrays.asList(constraintAnnotation.allowedFormats()));
    }

    @Override
    public boolean isValid(List<MultipartFile> images, ConstraintValidatorContext constraintValidatorContext) {
        if(images == null || images.isEmpty()){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Images list cannot be empty or null").addConstraintViolation();
            return false;
        }
        if(images.size()<minCount || images.size()>maxCount){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("images count must be between "+minCount+" and "+maxCount).addConstraintViolation();
            return false;
        }

        for (MultipartFile image : images) {
            if(image.isEmpty()){
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Image cannot be empty").addConstraintViolation();
                return false;
            }
            if (image.getSize()<minSize || image.getSize()>maxSize){
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Image size must be between "+minSize+"bytes and "+maxSize + " bytes").addConstraintViolation();
                return false;
            }
            if(!allowedFormats.contains(image.getContentType())){
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Invalid image format. Allowed formats  are: " + String.join(", ",allowedFormats)).addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
