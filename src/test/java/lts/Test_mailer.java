package lts;
import lts.Mailer.*;


/** @see lts.Mailer */
public class Test_mailer {


    ////////// Methods //////////
    // @Test
    public void send() throws Exception {

        String my_mail = "your_mail@gmail.com";
        String password = "your_password";
        String application_password = "#### #### #### ####";

        String to = "other_mail@yandex.ru";
        String subject = "Attempt_1";
        String text = File_manager._set_file("src/test/resources/Mailer/index.html", true)._read();

        Sender_builder sender_builder = new Sender_builder() {

            @Override
            public String _set_my_mail() {

                return my_mail;
            }

            @Override
            public String _set_password() {

                return password;
            }

            @Override
            public String _set_application_password() {

                return application_password;
            }

            @Override
            public Smtp_host _set_smtp_host() {

                return Smtp_host.GMAIL;
            }

        };

        Recipient_builder recipient_builder = new Recipient_builder() {

            @Override
            public String _set_to() {

                return to;
            }

            @Override
            public String _set_subject() {

                return subject;
            }

            @Override
            public String _set_text() {

                return text;
            }

            @Override
            public Text_type _set_text_type() {

                return Text_type.HTML;
            }

        };

        Mailer._send(sender_builder, recipient_builder);

    }


}