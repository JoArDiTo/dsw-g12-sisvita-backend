package com.sisvita_grupo12.vigilance_service.service;

import com.sisvita_grupo12.vigilance_service.dto.EmailRequest;
import com.sisvita_grupo12.vigilance_service.dto.EmailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailResponse sendSimpleMessage(EmailRequest emailRequest) {
         SimpleMailMessage message = new SimpleMailMessage();
         message.setTo(emailRequest.getEmail());
         message.setSubject(emailRequest.getSubject());
         message.setText(emailRequest.getBody());
         emailSender.send(message);
         return mapToResponse(message);
    }

    private EmailResponse mapToResponse(SimpleMailMessage message) {
        return EmailResponse.builder()
                .email(message.getTo())
                .subject(message.getSubject())
                .body(message.getText())
                .build();
    }
}
