package az.rentall.mvp.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FaqsResponse {
    private Long id;
    private String question;
    private String answer;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private Long categoryId;
}
