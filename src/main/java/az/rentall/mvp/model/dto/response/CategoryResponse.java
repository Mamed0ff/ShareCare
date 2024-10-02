package az.rentall.mvp.model.dto.response;


import az.rentall.mvp.model.entity.Faqs;
import az.rentall.mvp.model.entity.ProductEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    private String name;
    private String description;

    List<Faqs> faqs;
    List<ProductEntity> products;
}
