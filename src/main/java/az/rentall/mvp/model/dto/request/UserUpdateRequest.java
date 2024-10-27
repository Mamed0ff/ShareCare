package az.rentall.mvp.model.dto.request;

import az.rentall.mvp.util.annotation.ValidEmail;
import az.rentall.mvp.util.annotation.ValidPhoneNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserUpdateRequest {

    @ValidEmail
    private String email;

    @ValidPhoneNumber
    private String phoneNumber;

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-zƏəÖöÜüŞşÇçığ]+$", message = "Name must contain only Azerbaijani letters")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String surname;

    private String photoUrl;

    private LocalDate birthDay;
}
