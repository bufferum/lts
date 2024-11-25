package lts.Test_encrypter;
import java.io.File;
import org.junit.Test;

import lts.Encrypter;
import lts.Print;


/** @see lts.Encrypter */
public class Aes {


    ////////// Methods //////////
    @Test
    public void AES_encrypt() throws Exception {

        ////////// Variables //////////
        String RESOURCES = "src/test/resources/Encrypter/";
        File key_file_aes = new File(RESOURCES + "secret_key_aes");
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

    @Test
    public void AES_decrypt() throws Exception {

        ////////// Variables //////////
        String RESOURCES = "src/test/resources/Encrypter/";
        File key_file_aes = new File(RESOURCES + "secret_key_aes");
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