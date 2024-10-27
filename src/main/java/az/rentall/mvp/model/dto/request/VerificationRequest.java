package az.rentall.mvp.model.dto.request;

import lombok.Getter;

@Getter
public class VerificationRequest {
    private String email;
    private String verificationCode;
}
