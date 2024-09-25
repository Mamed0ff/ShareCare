package az.rentall.mvp.model.dto.response;


import az.rentall.mvp.model.entity.CategoriesEntity;
import az.rentall.mvp.model.entity.ProdImages;
import az.rentall.mvp.model.entity.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    String name;

    String description;

    String location;

    UserEntity owner;

    CategoriesEntity category;

    List<ProdImages> images;
}
