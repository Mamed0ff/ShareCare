package az.rentall.mvp.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductImageResponse {
    private Long id;
    private String path;
    private Boolean main;
}
