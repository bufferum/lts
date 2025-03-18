package lts.encrypter;
import org.junit.Rule;
import org.junit.Test;
import lts.Encrypter;
import lts.Reporter_tests;
import lts.signs.Print;


/** @see lts.Encrypter */
public class Test_encrypter_sha256 {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    @Test public void generate() {

        String text = "Hello, World!";
        String hash = Encrypter.SHA256._generate(text);

        Print.test("SHA256 hash for '" + text + "' = " + hash);

    }


}