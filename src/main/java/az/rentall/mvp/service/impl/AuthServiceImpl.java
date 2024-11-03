package az.rentall.mvp.service.impl;

import az.rentall.mvp.exception.*;
import az.rentall.mvp.mapper.UserMapper;
import az.rentall.mvp.model.Enums.RoleType;
import az.rentall.mvp.model.dto.request.ResetPassword;
import az.rentall.mvp.model.dto.request.UserLoginRequest;
import az.rentall.mvp.model.dto.request.UserRegisterRequest;
import az.rentall.mvp.model.dto.request.VerificationRequest;
import az.rentall.mvp.model.dto.response.TokenResponse;
import az.rentall.mvp.model.entity.UserEntity;
import az.rentall.mvp.repository.UserRepository;
import az.rentall.mvp.security.JwtTokenProvider;
import az.rentall.mvp.service.AuthService;
import az.rentall.mvp.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtService;
    private final EmailSenderService mailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userService;



    @Override
    public void register(UserRegisterRequest userRegisterRequest) {
        userRepository.findByEmail(userRegisterRequest.getEmail()).ifPresent(user -> {
            throw new AlreadyExistsException("EMAIL_ALREADY_EXISTS");
        });
        UserEntity user = UserMapper.INSTANCE.registerRequestToEntity(userRegisterRequest);
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        user.setRoleType(RoleType.USER);
        user.setVerificationCode(generateVerificationCode());
        user.setExpiryDate(LocalDateTime.now().plusMinutes(5));
        user.setCreated_at(LocalDateTime.now());
        userRepository.save(user);

        String subject = "Verification Code";
        mailService.sendEmail(user.getEmail(),subject, user.getVerificationCode());
    }

    @Override
    public TokenResponse login(UserLoginRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail()).orElseThrow(() ->
                new NotFoundException("USER_NOT_FOUND"));
        if(!user.getIsVerified()){
            throw new NotVerifiedException("USER_NOT_VERIFIED");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        TokenResponse tokenResponse=new TokenResponse();
        tokenResponse.setEmail(request.getEmail());
        tokenResponse.setToken(jwtTokenProvider.generateToken((UserDetails) authentication.getPrincipal()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Authentication Security Context Holder:{}",SecurityContextHolder.getContext().getAuthentication().getName());
        return tokenResponse;
    }

    @Override
    public void verifyAccount(VerificationRequest verificationRequest) {
        var user = userRepository.findByEmail(verificationRequest.getEmail()).orElseThrow(() ->
                new NotFoundException("USER_NOT_FOUND"));
        if (!user.getVerificationCode().equals(verificationRequest.getVerificationCode())){
            throw new InvalidTokenException("INVALID_VERIFICATION_CODE");
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime codeGeneratedAt = user.getExpiryDate();
        if(codeGeneratedAt != null && now.isAfter(codeGeneratedAt)){
            throw new TokenExpiredException("VERIFICATION_CODE_EXPIRED");
        }
        user.setIsVerified(true);
        userRepository.save(user);
    }

    @Override
    public void resendVerificationCode(String email) {
        var user = userRepository.findByEmail(email).orElseThrow(()->
            new NotFoundException("USER_NOT_FOUND"));
        user.setVerificationCode(generateVerificationCode());
        user.setExpiryDate(LocalDateTime.now().plusMinutes(5));
        userRepository.save(user);
        String subject = "Verification Code";
        mailService.sendEmail(user.getEmail(), subject, user.getVerificationCode());
    }

    @Override
    public void updatePassword(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() ->
                new NotFoundException("USER_NOT_FOUND"));
        UserDetails userDetails= userService.loadUserByUsername(user.getEmail());
        String token = jwtService.generateToken(userDetails);
        String resetLink = "https://sharecare.site/auth/reset-password?token=" + token;
        String subject = "Password Reset";
        mailService.sendEmail(user.getEmail(), subject, resetLink);
    }

    @Override
    public void resetPassword(String token, ResetPassword resetPassword) {
        if(!jwtTokenProvider.validateToken(token)){
            throw new InvalidTokenException("INVALID_TOKEN");
        }
        String email = jwtTokenProvider.tokenParser(token).getSubject();
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() ->
                new NotFoundException("USER_NOT_FOUND"));
        user.setPassword(passwordEncoder.encode(resetPassword.getPassword()));
        userRepository.save(user);
    }

    private String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        int otp = random.nextInt(1000, 10000);
        return String.valueOf(otp);
    }
}

