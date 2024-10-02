package az.rentall.mvp.model.dto.request;

import az.rentall.mvp.model.Enums.RoleType;
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
public class UserRequest {
    @NotNull
    Long id;

    @NotBlank
    String name;

    @NotBlank
    String surname;

    @NotBlank
    String username;

    @NotBlank
    String gmail;

    @NotBlank
    String password;

    @NotBlank
    String phoneNumber;

    @NotBlank
    String profileImage; // Saving path here, actual picture will be stored in images folder

    @NotBlank
    Boolean isVerified;

    @NotBlank
    RoleType roleType;
}
