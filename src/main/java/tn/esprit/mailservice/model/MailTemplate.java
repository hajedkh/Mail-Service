package tn.esprit.mailservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailTemplate {

    private String destinationAddress;

    private String subject;

    private String cc;

    private String body;
}
