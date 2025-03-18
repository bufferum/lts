package lts.encrypter;
import java.io.File;
import org.junit.Rule;
import org.junit.Test;
import lts.Encrypter;
import lts.Reporter_tests;
import lts.files.File_manager;
import lts.signs.Print;


/** @see lts.Encrypter */
public class Test_encrypter_aes {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    @Test public void encrypt() throws Exception {

        ////////// Variables //////////
        String RESOURCES = "src/test/resources/Test_encrypter/";
        File key_file_aes = File_manager._set_file(RESOURCES + "secret_key_aes", true)._get_file();
        String PASSWORD = "12345ABCDE123456_characters starting with a dash do not affect the key";
        String text = "_MY_TEXT_MY_TEXT_MY_TEXT_MY_TEXT_MY_TEXT3000";

        // encrypt
        String crypt = "";

        crypt = Encrypter.AES
                            ._create_secret_key(PASSWORD, key_file_aes)
                            ._encrypt(text);

        Print.test("Was: " + text + "\n\n");
        Print.test("Has become: " + crypt + "\n\n");

    }

    @Test public void decrypt() throws Exception {

        ////////// Variables //////////
        String RESOURCES = "src/test/resources/Test_encrypter/";
        File key_file_aes = File_manager._set_file(RESOURCES + "secret_key_aes", true)._get_file();
        String PASSWORD = "12345ABCDE123456_characters starting with a dash do not affect the key";
        String text = "_MY_TEXT_MY_TEXT_MY_TEXT_MY_TEXT_MY_TEXT3000";

        // encrypt
        String crypt = "";

        crypt = Encrypter.AES
                            ._create_secret_key(PASSWORD, key_file_aes)
                            ._encrypt(text);


        Print.test("Was: " + text + "\n\n");
        Print.test("Has become: " + crypt + "\n\n");


        // decrypt
        String decrypt = "";

        decrypt = Encrypter.AES
                            ._set_secret_key(key_file_aes)
                            ._decrypt(crypt);

        Print.test("Decrypt: " + decrypt + "\n\n");

    }


}