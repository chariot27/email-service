package br.ars.email_service.controller;

import br.ars.email_service.dto.EmailRequestDTO;
import br.ars.email_service.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService service;

    public EmailController(EmailService service) {
        this.service = service;
    }

    @PostMapping("/sucesso")
    public ResponseEntity<Void> emailSucesso(@RequestBody EmailRequestDTO dto) {
        service.enviarEmailAtivacao(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/falha")
    public ResponseEntity<Void> emailFalha(@RequestBody EmailRequestDTO dto) {
        service.enviarEmailCancelamento(dto);
        return ResponseEntity.ok().build();
    }
}
