package lts;
import lts.encrypter.AES;
import lts.encrypter.RSA;
import lts.encrypter.SHA256;


/**
 * <h4>This interface provides classes that deal with encryption.</h4>
 *
 * @patterns Singleton, Builder
 * @version 2.0
 * @author bufferum
 */
public interface Encrypter {


    ////////// Variables //////////
    public static final AES AES = new AES();
    public static final RSA RSA = new RSA();
    public static final SHA256 SHA256 = new SHA256();


}