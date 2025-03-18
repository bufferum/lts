package lts.encrypter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;


/** @see lts.Encrypter */
@DefaultQualifier(NonNull.class)
public class SHA256 {


    ////////// Methods //////////
    public String _generate(String input) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA256");
            byte[] message_digest = md.digest(input.getBytes()); // Calculating the hash
            StringBuilder hex_string = new StringBuilder(); // Converting bytes to hexadecimal representation

            for(byte b : message_digest) {

                String hex = Integer.toHexString(0xff & b);

                if(hex.length() == 1) {

                    hex_string.append('0');

                }

                hex_string.append(hex);

            }

            return hex_string.toString();
        }
        catch(NoSuchAlgorithmException e) { throw new RuntimeException(e); }

    }


}