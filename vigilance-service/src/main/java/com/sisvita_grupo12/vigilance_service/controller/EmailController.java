package com.sisvita_grupo12.vigilance_service.controller;

import com.sisvita_grupo12.vigilance_service.dto.EmailRequest;
import com.sisvita_grupo12.vigilance_service.dto.EmailResponse;
import com.sisvita_grupo12.vigilance_service.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/dto/insert")
    public EmailResponse insertEmail(@RequestBody EmailRequest emailRequest) {
        return emailService.sendSimpleMessage(emailRequest);
    }
}
