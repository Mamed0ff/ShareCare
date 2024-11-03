package az.rentall.mvp.controller;

import az.rentall.mvp.model.dto.request.ResetPassword;
import az.rentall.mvp.model.dto.request.UserLoginRequest;
import az.rentall.mvp.model.dto.request.UserRegisterRequest;
import az.rentall.mvp.model.dto.request.VerificationRequest;
import az.rentall.mvp.model.dto.response.JwtResponse;
import az.rentall.mvp.model.dto.response.TokenResponse;
import az.rentall.mvp.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        authService.register(userRegisterRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        return ResponseEntity.ok(authService.login(userLoginRequest));
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyAccount(@RequestBody @Valid VerificationRequest verificationRequest) {
        authService.verifyAccount(verificationRequest);
        return new ResponseEntity<>("Account verified successfully", HttpStatus.OK);
    }

    @PostMapping("/resend-verification-code")
    public ResponseEntity<String> resendVerificationCode(@RequestParam("email") String email) {
        authService.resendVerificationCode(email);
        return new ResponseEntity<>("Verification code sent successfully", HttpStatus.OK);
    }

    @PostMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestParam("email") String email) {
        authService.updatePassword(email);
        return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam("token") String token, @RequestBody ResetPassword resetPassword) {
        authService.resetPassword(token, resetPassword);
        return new ResponseEntity<>("Password reset successfully", HttpStatus.OK);
    }
}
