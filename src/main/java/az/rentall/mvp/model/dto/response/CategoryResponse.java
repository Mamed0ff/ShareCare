package az.rentall.mvp.model.dto.response;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
    private Integer prodCount;
}
