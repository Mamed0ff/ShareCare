package az.rentall.mvp.model.dto.request;

import lombok.Getter;


@Getter
public class ResetPassword {
    private String email;
    private String password;
}
