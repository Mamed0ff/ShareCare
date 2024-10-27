package az.rentall.mvp.service.impl;

import az.rentall.mvp.model.dto.request.ResetPassword;
import az.rentall.mvp.model.dto.request.UserLoginRequest;
import az.rentall.mvp.model.dto.request.UserRegisterRequest;
import az.rentall.mvp.model.dto.request.VerificationRequest;
import az.rentall.mvp.model.dto.response.JwtResponse;
import az.rentall.mvp.repository.UserRepository;
import az.rentall.mvp.service.AuthService;
import az.rentall.mvp.service.EmailSenderService;
import az.rentall.mvp.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final EmailSenderService mailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public void register(UserRegisterRequest request) {

    }

    @Override
    public JwtResponse login(UserLoginRequest request) {
        return null;
    }

    @Override
    public void verifyAccount(VerificationRequest request) {

    }

    @Override
    public void resendVerificationCode(String email) {

    }

    @Override
    public void updatePassword(String email) {

    }

    @Override
    public void resetPassword(String token, ResetPassword resetPassword) {

    }
}
