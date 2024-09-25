package az.rentall.mvp.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
public class UserCabinetResponse {
    private Long id;
    private String address;
    private LocalDate creationDate;
    private LocalDate updateDate;
}
