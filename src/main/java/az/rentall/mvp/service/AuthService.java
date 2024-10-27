package az.rentall.mvp.service;


import az.rentall.mvp.model.dto.request.ResetPassword;
import az.rentall.mvp.model.dto.request.UserLoginRequest;
import az.rentall.mvp.model.dto.request.UserRegisterRequest;
import az.rentall.mvp.model.dto.request.VerificationRequest;
import az.rentall.mvp.model.dto.response.JwtResponse;

public interface AuthService {
    public void register(UserRegisterRequest request);
    public JwtResponse login(UserLoginRequest request);
    public void verifyAccount(VerificationRequest request);
    public void resendVerificationCode(String email);
    public void updatePassword(String email);
    public void resetPassword(String token, ResetPassword resetPassword);
}
