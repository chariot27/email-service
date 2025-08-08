package br.ars.email_service.service;

import br.ars.email_service.dto.EmailRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmailAtivacao(EmailRequestDTO dto) {
        String assunto = "✅ Assinatura Ativada com Sucesso!";
        String mensagem = String.format("""
                Olá %s,

                Obrigado por sua assinatura! Seu pagamento foi confirmado e sua assinatura já está ativa.

                Aproveite todos os benefícios da nossa plataforma.

                Atenciosamente,
                Equipe Ars
                """, dto.getNome());

        enviar(dto.getDestinatario(), assunto, mensagem);
    }

    public void enviarEmailCancelamento(EmailRequestDTO dto) {
        String assunto = "❌ Falha no Pagamento - Assinatura Inativa";
        String mensagem = String.format("""
                Olá %s,

                Infelizmente não conseguimos confirmar o pagamento da sua assinatura.
                Sua conta está atualmente inativa.

                Caso tenha ocorrido algum problema com o pagamento, tente novamente ou entre em contato com o suporte.

                Atenciosamente,
                Equipe Ars
                """, dto.getNome());

        enviar(dto.getDestinatario(), assunto, mensagem);
    }

    private void enviar(String para, String assunto, String corpo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(para);
        message.setSubject(assunto);
        message.setText(corpo);
        mailSender.send(message);
    }
}
