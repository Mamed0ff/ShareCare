package az.rentall.mvp.service.impl;

import az.rentall.mvp.service.EmailSenderUtil;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailSenderUtilImpl implements EmailSenderUtil {
    private final JavaMailSender javaMailSender;

    @Override
    public void sendOtp(String toEmail, String otpCode) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        try {
            message.setTo(toEmail);
            message.setSubject("Verify otp");
            message.setText("Your otp code is: " + otpCode);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            log.error(ex.getMessage());
        }

    }
}
