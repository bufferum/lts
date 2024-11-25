package lts.Test_encrypter;
import org.junit.Test;
import lts.Encrypter;
import lts.Print;


/** @see lts.Encrypter */
public class Sha256 {


    ////////// Methods //////////
    @Test
    public void SHA256_generate() {

        String text = "Hello, World!";
        String hash = Encrypter.SHA256._generate(text);

        Print.test("SHA256 hash for '" + text + "' = " + hash);

    }


}