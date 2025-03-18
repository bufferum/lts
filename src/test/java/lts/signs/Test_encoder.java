package lts.signs;
import org.junit.Rule;
import org.junit.Test;
import lts.Reporter_tests;
import lts.signs.Encoder.Encoding;


/** @see lts.signs.Encoder */
public class Test_encoder {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    @Test public void translate_to_utf_8() {

        // Crypt
        String password = "My super password, никто никогда не взломает!";
        Byte[] array_utf_8 = Encoder._translate_to_encoding(Encoding.UTF_8, password);

        Print.test("My password:\n" + password + "\n\nMy password to UTF-8:\n");
        for(Byte b : array_utf_8) {
            Print.test(b + " ");
        }

    }

    @Test public void translate_from_utf_8() throws Exception {

        // Crypt
        String password = "My super password, никто никогда не взломает!";
        Byte[] array_utf_8 = Encoder._translate_to_encoding(Encoding.UTF_8, password);


        Print.test("My password to UTF-8:\n");

        for(Byte b : array_utf_8) {

            Print.test(b + " ");

        }


        // Decrypt
        Print.test(
            "\n\nMy decrypted password:\n"
            + Encoder._translate_to_str(Encoding.UTF_8, array_utf_8)
        );

    }

    @Test public void translate_to_cp1252() {

        // Crypt
        String password = "My super password, никто никогда не взломает!";
        Byte[] array_utf_8 = Encoder._translate_to_encoding(Encoding.CP_1252, password);

        Print.test("My password:\n" + password + "\n\nMy password to CP1252:\n");

        for(Byte b : array_utf_8) {

            Print.test(b + " ");

        }

    }

    @Test public void translate_from_cp1252() throws Exception {

        // Crypt
        String password = "My super password, никто никогда не взломает!";
        Byte[] array_utf_8 = Encoder._translate_to_encoding(Encoding.CP_1252, password);


        Print.test("My password to CP1252:\n");

        for(Byte b : array_utf_8) {

            Print.test(b + " ");

        }


        // Decrypt
        Print.test(
            "\n\nMy decrypted password:\n"
            + Encoder._translate_to_str(Encoding.CP_1252, array_utf_8)
        );

    }


}