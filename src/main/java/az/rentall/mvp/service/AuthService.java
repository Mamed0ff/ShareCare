package az.rentall.mvp.service;


import az.rentall.mvp.model.dto.request.ResetPassword;
import az.rentall.mvp.model.dto.request.UserLoginRequest;
import az.rentall.mvp.model.dto.request.UserRegisterRequest;
import az.rentall.mvp.model.dto.request.VerificationRequest;
import az.rentall.mvp.model.dto.response.TokenResponse;

public interface AuthService {
     void register(UserRegisterRequest request);
     TokenResponse login(UserLoginRequest request);
     void verifyAccount(VerificationRequest request);
     void resendVerificationCode(String email);
     void updatePassword(String email);
     void resetPassword(String token, ResetPassword resetPassword);
}
