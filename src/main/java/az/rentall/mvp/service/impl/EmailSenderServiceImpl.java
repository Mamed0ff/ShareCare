package az.rentall.mvp.service.impl;

import az.rentall.mvp.service.EmailSenderService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    @Async
    @Transactional
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = createMail(to, subject, text);
        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            throw new RuntimeException("Error sending email", e);
        }
    }

    private SimpleMailMessage createMail(String to, String subject, String textMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(fromEmail);
        message.setSubject(subject);
        message.setText(textMessage);
        return message;
    }
}
