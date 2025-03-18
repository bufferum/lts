package lts.webspace;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;


/**
 * <h4>This is a class that provides the ability to send emails to emails.</h4>
 *
 * <p>SMTP from Google is used.
 * <p>To work, an "application password" is required, in this case from Gmail.
 * <p>You can create an application password in this
 * <a href="https://myaccount.google.com/apppasswords">link</a>.
 *
 * @patterns Singleton
 * @version 2.0
 * @author bufferum
 */
@DefaultQualifier(NonNull.class)
public class Mailer {


    ////////// Constructors //////////
    private Mailer() { }


    ////////// Interfaces //////////
    /** Setting parameters for the sender. */
    public interface Sender_builder {

        public String _set_my_mail();
        public String _set_password();
        public String _set_application_password();
        public Smtp_host _set_smtp_host();

    }

    /** Setting parameters for the recipient. */
    public interface Recipient_builder {

        public String _set_to();
        public String _set_subject();
        public String _set_text();
        public Text_type _set_text_type();

    }


    ////////// Enums //////////
    public enum Smtp_host {

        GMAIL("smtp.gmail.com");

        String host;

        Smtp_host(String host) {

            this.host = host;

        }

        @Override
        public String toString() {

            return this.host.toString();
        }

    }

    public enum Text_type {

        HTML("text/html"),
        TEXT("text/plain");

        String text_type;

        Text_type(String text_type) {

            this.text_type = text_type;

        }

        @Override
        public String toString() {

            return this.text_type.toString();
        }

    }


    ////////// Methods //////////
    public static void _send(Sender_builder sender_builder, Recipient_builder recipient_builder) {

        try {

            // Creating a session
            Properties properties = new Properties();
                       properties.setProperty("mail.transport.protocol", "smtps");
                       properties.setProperty("mail.smtp.auth", "true");
                       properties.setProperty("mail.smtp.starttls.enable", "true");
                       properties.setProperty("mail.smtp.port", "465");
                       properties.setProperty("mail.smtp.socketFactory.port", "465");
                       properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                       properties.setProperty("mail.smtp.host", sender_builder._set_smtp_host().toString());
                       properties.setProperty("mail.smtp.user", sender_builder._set_my_mail());
                       properties.setProperty("mail.smtp.password", sender_builder._set_password());

            Session session = Session.getInstance(properties, new Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication(
                        sender_builder._set_my_mail(),
                        sender_builder._set_application_password()
                    );
                }

            });

            // Creating the context of a letter
            Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(sender_builder._set_my_mail()));
                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient_builder._set_to()));
                    message.setSubject(recipient_builder._set_subject());
                    message.setContent(recipient_builder._set_text(), recipient_builder._set_text_type().toString() + "; charset=utf-8");

            Transport.send(message);

        }
        catch(NoSuchProviderException e) { e.printStackTrace(); }
        catch(AddressException e) { e.printStackTrace(); }
        catch(MessagingException e) { e.printStackTrace(); }

    }

}