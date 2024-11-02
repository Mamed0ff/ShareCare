package az.rentall.mvp.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarouselRequest {

    private String title;

    private String description;
}
