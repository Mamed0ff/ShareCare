package az.rentall.mvp.model.dto.request;

import az.rentall.mvp.model.Enums.RoleType;
import az.rentall.mvp.util.annotation.ValidEmail;
import az.rentall.mvp.util.annotation.ValidImages;
import az.rentall.mvp.util.annotation.ValidPassword;
import az.rentall.mvp.util.annotation.ValidPhoneNumber;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterRequest {

    @ValidEmail
    String email;

    @ValidPassword
    String password;


    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-zƏəÖöÜüŞşÇçığ]+$", message = "Name must contain only Azerbaijani letters")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Pattern(regexp = "^\\+994\\d{9}$", message = "Phone number must be in the format +994XXXXXXXXX")
    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;

    String username;
}
