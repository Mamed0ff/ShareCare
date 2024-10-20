package az.rentall.mvp.model.dto.response;


import az.rentall.mvp.model.entity.CategoriesEntity;
import az.rentall.mvp.model.entity.ProductImages;
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
    Long id;
    String name;
    String description;
    String location;
    Integer viewCount;
    Boolean isOld;
    UserEntity owner;
    CategoriesEntity category;

    List<ProductImages> images;
}
