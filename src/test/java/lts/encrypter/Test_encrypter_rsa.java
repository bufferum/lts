package lts.encrypter;
import java.io.File;
import org.junit.Rule;
import org.junit.Test;
import lts.Encrypter;
import lts.Reporter_tests;
import lts.encrypter.RSA.Provider_key;
import lts.signs.Print;


/** @see lts.Encrypter */
public class Test_encrypter_rsa {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    @Test public void crypt() throws Exception {

        ////////// Variables //////////
        String RESOURCES = "src/test/resources/Test_encrypter/";
        String text = "_MY_TEXT_MY_TEXT_MY_TEXT_MY_TEXT_MY_TEXT3000";
        File key_public = null;
        String key_private = "null";
        String crypt = "null";

        // crypt
        RSA rsa = Encrypter.RSA;

        // Getting the keys in a format that is convenient for me.
        Provider_key provider_key = new Provider_key();
            key_public = provider_key._get_key_public_SERIALIZE(RESOURCES + "key_public_rsa");
            key_private = provider_key._get_key_private_STRING();

        crypt = rsa._encrypt(text, key_public);

        Print.test("Encryt: " + crypt + "\n\n");

        Print.test("Keys:\n");
        Print.test("Key public: " + key_public.toPath() + "\n");
        Print.test("Key private: " + key_private + "\n");

    }

    @Test public void decrypt() throws Exception {

        ////////// Variables //////////
        String RESOURCES = "src/test/resources/Test_encrypter/";
        String text = "_MY_TEXT_MY_TEXT_MY_TEXT_MY_TEXT_MY_TEXT3000";
        File key_public = null;
        String key_private = "null";
        String crypt = "null";

        // crypt
        // Getting the keys in a format that is convenient for me.
        Provider_key provider_key = new Provider_key();
            key_public = provider_key._get_key_public_SERIALIZE(RESOURCES + "key_private_rsa");
            key_private = provider_key._get_key_private_STRING();

        crypt = Encrypter.RSA._encrypt(text, key_public);

        Print.test("Key public: " + key_public.toPath() + "\n");
        Print.test("Key private: " + key_private + "\n");
        Print.test("\nEncryt: " + crypt + "\n\n");


        // decrypt
        Print.test("\nDecrypt:\n");
        Print.test(Encrypter.RSA._decrypt(crypt, key_private));

    }


}