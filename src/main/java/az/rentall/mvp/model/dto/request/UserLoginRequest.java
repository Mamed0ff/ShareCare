package az.rentall.mvp.model.dto.request;

import az.rentall.mvp.util.annotation.ValidEmail;
import az.rentall.mvp.util.annotation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {
    @ValidEmail
    private String email;

    @ValidPassword
    private String password;
}
