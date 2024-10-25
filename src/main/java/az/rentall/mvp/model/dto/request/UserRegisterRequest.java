package az.rentall.mvp.model.dto.request;

import az.rentall.mvp.model.Enums.RoleType;
import az.rentall.mvp.util.annotation.ValidEmail;
import az.rentall.mvp.util.annotation.ValidImages;
import az.rentall.mvp.util.annotation.ValidPassword;
import az.rentall.mvp.util.annotation.ValidPhoneNumber;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterRequest {
    @NotNull
    Long id;

    @NotBlank
    String name;

    @NotBlank
    String surname;

    @NotBlank
    String username;

    @NotBlank
    @ValidEmail
    String gmail;

    @NotBlank
    @ValidPassword
    String password;
    
}
