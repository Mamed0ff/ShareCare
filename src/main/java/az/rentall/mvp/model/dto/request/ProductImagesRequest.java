package az.rentall.mvp.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductImagesRequest {
    private Long productId;
    private String path;
    private Boolean main;
}
