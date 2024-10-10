package az.rentall.mvp.model.dto.request;

import az.rentall.mvp.model.entity.CategoriesEntity;
import az.rentall.mvp.model.entity.ProductImages;
import az.rentall.mvp.model.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    @NotBlank
    String name;

    @NotBlank
    String description;

    @NotBlank
    String location;

    @NotNull
    UserEntity owner;

    @NotNull
    CategoriesEntity category;

    @NotNull
    List<ProductImages> images;
}