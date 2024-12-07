package az.rentall.mvp.model.dto.request;

import az.rentall.mvp.util.annotation.ValidPassword;
import lombok.Getter;


@Getter
public class ResetPassword {
    @ValidPassword
    private String password;
}
