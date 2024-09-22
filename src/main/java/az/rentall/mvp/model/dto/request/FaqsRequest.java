package az.rentall.mvp.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FaqsRequest {

    @NotNull(message ="Question cannot be empty")
    @Size(min = 2, max = 255,message = "Question length must be in 2-255 characters")
    private String question;

    @NotBlank(message = "Answer cannot be empty")
    private String answer;

    @NotNull(message = "category must be selected")
    private Long categoryId;
}
