package az.rentall.mvp.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ProductImagesRequest {
    private Long productId;
    private String path;
    private Boolean main;
}
