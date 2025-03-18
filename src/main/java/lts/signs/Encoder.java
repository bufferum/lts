package lts.signs;
import java.nio.charset.Charset;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;


/**
 * <h4>This is a class for converting text into an array of bytes
 * of the specified encoding and back.</h4>
 * <p>Exemple: <p>(String) {@code "Hello world"} --~UTF_8-->
 *             <p>(Byte[]) {@code 72 101 108 108 111 32 119 111 114 108 100}<hr>
 *
 * @patterns Singleton
 * @version 2.0
 * @author bufferum
 */
@DefaultQualifier(NonNull.class)
public class Encoder {


    ////////// Constructors //////////
    private Encoder() { }


    ////////// Enums //////////
    public enum Encoding {

        UTF_8("utf8"),
        CP_1252("cp1252");

        private String encoding;

        Encoding(String encoding) {

            this.encoding = encoding;

        }

        @Override
        public String toString() {

            return this.encoding;
        }

    }


    ////////// Methods //////////
    /**
     * Returns the encoded {@code str} as {@code Byte[]}
     * in the specified encoding.
     */
    public static Byte[] _translate_to_encoding(Encoding from_encoding, String str) {

        return Converter._convert(str.getBytes(Charset.forName(from_encoding.toString())));
    }

    /**
     * Returns the decoded {@code str}, which was originally
     * in the form of {@code Byte[]}.
     */
    public static String _translate_to_str(Encoding to_encoding, Byte[] utf_code) throws Exception {

        return new String(Converter._to_primitive(utf_code), Charset.forName(to_encoding.toString()));
    }


}