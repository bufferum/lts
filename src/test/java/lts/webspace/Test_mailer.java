package lts.webspace;
import org.junit.Rule;
import lts.Reporter_tests;
import lts.files.File_manager;
import lts.webspace.Mailer.*;


/** @see lts.webspace.Mailer */
public class Test_mailer {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    // @Test
    public void send() throws Exception {

        String my_mail = "your_mail@gmail.com";
        String password = "your_password";
        String application_password = "#### #### #### ####";

        String to = "other_mail@yandex.ru";
        String subject = "Attempt_1";
        String text = File_manager._set_file("src/test/resources/Test_mailer/index.html", true)._read();

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