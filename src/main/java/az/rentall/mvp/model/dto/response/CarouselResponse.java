package az.rentall.mvp.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CarouselResponse {
    private Long id;

    private String title;

    private String description;

    private String imageUrl;
}
