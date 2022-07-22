package tn.esprit.mailservice.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import tn.esprit.mailservice.model.MailTemplate;

@Service
public class EmailServiceImpl   {


    @Autowired
    MailSender mailSender;


    @JmsListener(destination = "${mailSender.jms.queue}")
    public void sendMail(String mail) throws JsonProcessingException {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        SimpleMailMessage msg = new SimpleMailMessage(simpleMailMessage);
        ObjectMapper objectMapper = new ObjectMapper();
       MailTemplate mailTemplate=  objectMapper.readValue(mail, MailTemplate.class);
        msg.setTo(mailTemplate.getDestinationAddress());
        msg.setSubject(mailTemplate.getSubject());
        msg.setText(mailTemplate.getBody());
        try{
            this.mailSender.send(msg);
        }
        catch(MailException ex) {
            throw new RuntimeException(ex);
        }

    }
}
