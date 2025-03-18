package lts.encrypter;
import java.io.File;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;

import lts.files.Serializator;


/**
 * <h4>ATTENTION!!!</h4>
 * <p>You cannot use the File format key if they were received in String format and vice versa.</p>
 *
 * @see lts.Encrypter
 */
@DefaultQualifier(NonNull.class)
public class RSA {


    ////////// Variables //////////
    private static final RSA RSA = new RSA();
    @Nullable private PublicKey key_public;
    @Nullable private PrivateKey key_private;


    ////////// Constructors //////////
    public RSA() { }


    ////////// Methods //////////
    /**
     * @param text - The text that needs to be encrypted.
     * @param public_key (String) - The text that represents the public key.
     */
    public String _encrypt(String text, String public_key) throws Exception {

        // Decode the Base64 string
        byte[] key_bytes = Base64.getDecoder().decode(public_key);

        // Restoring the key using the KeyFactory
        PKCS8EncodedKeySpec key_spec = new PKCS8EncodedKeySpec(key_bytes);
        KeyFactory key_factory = KeyFactory.getInstance("RSA");
        RSA.key_public = key_factory.generatePublic(key_spec);


        // Encrypting
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, RSA.key_public);

        byte[] encrypted_bytes = cipher.doFinal(text.getBytes());

        // Returning encrypted data in Base64 format
        return Base64.getEncoder().encodeToString(encrypted_bytes);
    }

    /**
     * @param text - The text that needs to be encrypted.
     * @param public_key (File) - A serialized file that contains a public key.
     */
    public String _encrypt(String text, File serialeze_file_public_key) throws Exception {

        // Restoring the key from the serialized file
        RSA.key_public = (PublicKey) Serializator._deserialize(serialeze_file_public_key);


        // Encrypting
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, RSA.key_public);

        byte[] encrypted_bytes = cipher.doFinal(text.getBytes());

        // Returning encrypted data in Base64 format
        return Base64.getEncoder().encodeToString(encrypted_bytes);
    }


    /**
     * Decrypt data using the following parameters:
     * @param encrypted_text - The encrypted text that needs to be decrypted.
     * @param private_key - (String) - The private key in the form of a String.
     */
    public String _decrypt(String encrypted_text, String private_key) throws Exception {

        // Decode the Base64 string
        byte[] key_bytes = Base64.getDecoder().decode(private_key);

        // Restoring the key using the KeyFactory
        PKCS8EncodedKeySpec key_spec = new PKCS8EncodedKeySpec(key_bytes);
        KeyFactory key_factory = KeyFactory.getInstance("RSA");
        RSA.key_private = key_factory.generatePrivate(key_spec);

        // Decoding data from the Base64 format
        byte[] encrypted_bytes = Base64.getDecoder().decode(encrypted_text);

        Cipher decipher = Cipher.getInstance("RSA");
        decipher.init(Cipher.DECRYPT_MODE, RSA.key_private);

        byte[] decrypted_bytes = decipher.doFinal(encrypted_bytes);

        // Convert the decrypted bytes back to a string
        return new String(decrypted_bytes);
    }

    /**
     * Decrypt data using the following parameters:
     * @param encrypted_text - The encrypted text that needs to be decrypted.
     * @param private_key - (File) - A serialized file containing a private key.
     */
    public String _decrypt(String encrypted_text, File serialeze_file_private_key) throws Exception {

        // Restoring the key from the serialized file
        RSA.key_private = (PrivateKey) Serializator._deserialize(serialeze_file_private_key);


        // Decoding data from the Base64 format
        byte[] encrypted_bytes = Base64.getDecoder().decode(encrypted_text);

        Cipher decipher = Cipher.getInstance("RSA");
        decipher.init(Cipher.DECRYPT_MODE, RSA.key_private);

        byte[] decrypted_bytes = decipher.doFinal(encrypted_bytes);

        // Convert the decrypted bytes back to a string
        return new String(decrypted_bytes);
    }


    ////////// Class //////////
    /** A class that provides methods for obtaining public and private keys. */
    public static class Provider_key {


        ////////// Constructors //////////
        public Provider_key() throws Exception {

            KeyPairGenerator key_gen = KeyPairGenerator.getInstance("RSA");
            key_gen.initialize(2048);
            KeyPair pair = key_gen.generateKeyPair();
            RSA.key_public = pair.getPublic();
            RSA.key_private = pair.getPrivate();

        }


        ////////// Methods //////////
        public String _get_key_public_STRING() {

            // Exporting the key to PKCS8 format
            byte[] public_key_bytes = RSA.key_public.getEncoded();

            // Convert the string to Base64
            String public_key_string = Base64.getEncoder().encodeToString(public_key_bytes);

            return public_key_string;
        }

        public File _get_key_public_SERIALIZE(String file_path) throws Exception {

            // Creating a serialized file that will contain the public key
            File key_public = new File(file_path);
            Serializator._serialize(key_public, RSA.key_public);

            return key_public;
        }

        public String _get_key_private_STRING() {

            // Exporting the key to PKCS8 format
            byte[] private_key_bytes = RSA.key_private.getEncoded();

            // Convert the string to Base64
            String private_key_string = Base64.getEncoder().encodeToString(private_key_bytes);

            return private_key_string;
        }

        public File _get_key_private_SERIALIZE(String file_path) throws Exception {

            // Creating a serialized file that will contain the private key
            File key_private = new File(file_path);
            Serializator._serialize(key_private, RSA.key_public);

            return key_private;
        }


    }


}