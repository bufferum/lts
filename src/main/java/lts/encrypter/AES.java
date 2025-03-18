package lts.encrypter;
import java.io.File;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;

import lts.files.Serializator;


/** @see lts.Encrypter */
@DefaultQualifier(NonNull.class)
public class AES {


    ////////// Variables //////////
    private static final AES AES = new AES();
    private static final Encrypt encrypt = new Encrypt();
    private static final Decrypt decrypt = new Decrypt();
    @Nullable private SecretKey key;


    ////////// Constructors //////////
    public AES() { }


    ////////// Methods //////////
    /**
     * <p>A method for generating a key based on a password. Without the key,
     * you will not be able to decrypt the cipher. Insert the necessary parameters
     * to start encrypting the text by calling the {@code _encrypt} method.
     *
     * @param password - Your password, on the basis of which the key will
     *                   be generated. When using the same password, the same
     *                   key will be generated.
     *                   <p>ATTENTION!!! The password is truncated to 16 characters.
     *                   Since the {@code copyOf} method requires exactly 16 bytes.
     *                   Therefore, if the first 16 characters in your new password
     *                   do not differ from the first password, then the key will
     *                   not change. But if your password consists of 1 or 16
     *                   characters, then key generation will still work.<p>
     * @param key_file - The file where the key will be saved for future
     *                   decryptions. If you re-use the same file to save the
     *                   key, thenew data will simply be overwritten.
     */
    public Encrypt _create_secret_key(String password, File key_file) throws Exception {

        byte[] key_bytes = password.getBytes("UTF-8");
        key_bytes = Arrays.copyOf(key_bytes, 16); // AES-128 requires 16 bytes
        AES.key = new SecretKeySpec(key_bytes, "AES");

        Serializator._serialize(key_file, AES.key);

        return encrypt;
    }

    /**
     * <p>This method is used to insert an existing key and then proceed
     * to decryption - the {@code _decrypt} method is called.
     *
     * @param key_file - The file where the key will be saved for future
     *                   decryptions. If you re-use the same file to save the
     *                   key, thenew data will simply be overwritten.
     */
    public Decrypt _set_secret_key(File key_file) throws Exception {

        AES.key = (SecretKey) Serializator._deserialize(key_file);

        return decrypt;
    }


    ////////// Class //////////
    public static class Encrypt {


        ////////// Constructors //////////
        private Encrypt() { }


        ////////// Methods //////////
        /**
         * <p>A method for encrypting text.
         * @param text - The text that you are going to encrypt.
         */
        public String _encrypt(String text) throws Exception {

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, AES.key);

            byte[] encrypted_bytes = cipher.doFinal(text.getBytes("UTF-8"));
            String result = Base64.getEncoder().encodeToString(encrypted_bytes);

            return result;
        }


    }

    public static class Decrypt {


        ////////// Constructors //////////
        private Decrypt() { }


        ////////// Methods //////////
        /**
         * <p>A method for decrypting data.
         * @param encrypted_text - The encrypted text that needs to be decrypted.
         */
        public String _decrypt(String encrypted_text) throws Exception {

            Cipher decipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            decipher.init(Cipher.DECRYPT_MODE, AES.key);

            byte[] decoded_bytes = Base64.getDecoder().decode(encrypted_text);
            byte[] decrypted_bytes = decipher.doFinal(decoded_bytes);

            return new String(decrypted_bytes, "UTF-8");
        }


    }


}