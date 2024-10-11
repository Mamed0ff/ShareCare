package az.rentall.mvp.service;

public interface EmailSenderService {
    public void sendOtp(String toEmail, String otpCode);
}
