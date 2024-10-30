package az.rentall.mvp.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CarouselRequest {

    private String title;

    private String description;
}
