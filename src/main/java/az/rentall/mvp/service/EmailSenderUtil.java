package az.rentall.mvp.service;

public interface EmailSenderUtil {
    public void sendOtp(String toEmail, String otpCode);
}
