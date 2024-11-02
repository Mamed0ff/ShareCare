package az.rentall.mvp.model.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;

    String name;

    String surname;

    String username;

    String email;

    String phoneNumber;

    String photoUrl; // Saving path here, actual picture will be stored in images folder

}
