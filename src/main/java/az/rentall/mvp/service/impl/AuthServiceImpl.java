package az.rentall.mvp.service.impl;

import az.rentall.mvp.model.Enums.RoleType;
import az.rentall.mvp.model.dto.request.ResetPassword;
import az.rentall.mvp.model.dto.request.UserLoginRequest;
import az.rentall.mvp.model.dto.request.UserRegisterRequest;
import az.rentall.mvp.model.dto.request.VerificationRequest;
import az.rentall.mvp.model.dto.response.JwtResponse;
import az.rentall.mvp.model.entity.UserEntity;
import az.rentall.mvp.repository.UserRepository;
import az.rentall.mvp.service.AuthService;
import az.rentall.mvp.service.EmailSenderService;
import az.rentall.mvp.service.jwt.JwtService;
import az.rentall.mvp.service.jwt.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final EmailSenderService mailService;
    private final UserDetailsImpl userDetails;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    private final AuthenticationManager authManager;

    @Override
    public void register(UserRegisterRequest userRegisterRequest) {
        //Register the user to repository and generate a token
        var user = UserEntity.builder()
                .username(userRegisterRequest.getUsername())
                .email(userRegisterRequest.getEmail())
                .password(encoder.encode(userRegisterRequest.getPassword()))
                .roleType(RoleType.USER)
                .build();
        repository.save(user);

        var jwtToken = jwtService.generateAccessToken((UserDetails) user);
        JwtResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public JwtResponse login(UserLoginRequest userLoginRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getEmail(),
                        userLoginRequest.getPassword()
                )
        );

        var user = repository.findByEmail(userLoginRequest.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateAccessToken((UserDetails) user);
        return JwtResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public void verifyAccount(VerificationRequest verificationRequest) {
        var user = repository.findByEmail(verificationRequest.getEmail()).orElseThrow();
        if (jwtService.validateToken(verificationRequest.getEmail())) {
            user.getIsVerified();
            repository.save(user);
        }
    }

    @Override
    public void resendVerificationCode(String email) {
        var user = repository.findByEmail(email).orElseThrow();
        var verificationToken = jwtService.generateAccessToken((UserDetails) user);
        mailService.sendEmail(user.getEmail(), "subject", "verification code");
    }

    @Override
    public void updatePassword(String email) {
        var user = repository.findByEmail(email).orElseThrow();
        var newPassword = passwordEncoder.encode("newPassword"); // Сгенерируйте или получите новый пароль
        user.setPassword(newPassword);
        repository.save(user);
    }

    @Override
    public void resetPassword(String token, ResetPassword resetPassword) {
        if (jwtService.validateToken(token)) {
            var user = repository.findByEmail(resetPassword.getEmail()).orElseThrow();
            user.setPassword(passwordEncoder.encode(resetPassword.getPassword()));
            repository.save(user);
        }
    }
}

