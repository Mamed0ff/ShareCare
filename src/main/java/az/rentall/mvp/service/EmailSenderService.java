package az.rentall.mvp.service;

public interface EmailSenderService {
    public void sendEmail(String toEmail, String subject, String text);
}
