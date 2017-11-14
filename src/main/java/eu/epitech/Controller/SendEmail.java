package eu.epitech.Controller;

import java.util.List;

import org.simplejavamail.converter.EmailConverter;
import org.simplejavamail.email.Email;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.ServerConfig;
import org.simplejavamail.mailer.config.TransportStrategy;
import javax.mail.*;

public class SendEmail {
    static public boolean sendEmail(List<String> recipients, String subject, String text)
    {
        try {
            Email email = new Email();
            email.setFromAddress("Area51", "area51.java@gmail.com");
            email.addBccRecipients("area51.java@gmail.com", String.join(",", recipients));
            email.setSubject(subject);
            email.setText(text);

            new Mailer("smtp.gmail.com", 587, "area51.java@gmail.com", "apdvesfjyeuuytoy", TransportStrategy.SMTP_TLS).sendMail(email);
            return (true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return (false);
        }
    }
}
