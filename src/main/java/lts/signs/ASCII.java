package lts.signs;
import java.util.ArrayList;
import java.util.List;


/**
 * <h4>Management for ASCII.</h4>
 *
 * @version 2.0
 * @author bufferum
 */
public class ASCII {


    ////////// Constructors //////////
    private ASCII() { }


    ////////// Enums //////////
    public enum Char_type {


        NUMBERS(48, 57),                // All the numbers
        LATIN_LOWERCASE(97, 122),       // All Latin letters are in lowercase
        LATIN_UPPERCASE(65, 90),        // All Latin letters are in uppercase
        CYRILLIC_LOWERCASE(1072, 1103), // All Cyrillic letters are in lowercase
        CYRILLIC_UPPERCASE(1040, 1071), // All Cyrillic letters are in uppercase

        SPECIAL_CHARACTERS_1(32, 47),   // All special characters
        SPECIAL_CHARACTERS_2(58, 64),
        SPECIAL_CHARACTERS_3(91, 96),
        SPECIAL_CHARACTERS_4(123, 126);


        ////////// Variables //////////
        private final Integer start;
        private final Integer end;


        ////////// Constructors //////////
        Char_type(Integer start, Integer end) {

            this.start = start;
            this.end = end;

        }


        ////////// Methods //////////
        public Integer _get_start() { return start; }
        public Integer _get_end() { return end; }


    }


    ////////// Methods //////////
    public static List<String> _get(Char_type char_type) {

        List<String> chars = new ArrayList<>();

        for(int i = char_type._get_start(); i <= char_type._get_end(); i++) {

            chars.add(_get_selectively(i));

        }

        return chars;
    }

    public static String _get_selectively(int i) {

        return String.valueOf((char) i);
    }


}