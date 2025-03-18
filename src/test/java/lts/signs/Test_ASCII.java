package lts.signs;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import lts.Reporter_tests;
import lts.signs.ASCII.Char_type;


/** @see lts.signs.ASCII */
public class Test_ASCII {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    @Test public void latin_lowercase() {

        Print.test("Латинские в нижнем регистре: ");
        List<String> latin_lowercase = ASCII._get(Char_type.LATIN_LOWERCASE);
        Print.result(latin_lowercase + "\n");

    }

    @Test public void latin_uppercase() {

        Print.test("Латинские в верхнем регистре: ");
        List<String> latin_uppercase = ASCII._get(Char_type.LATIN_UPPERCASE);
        Print.result(latin_uppercase + "\n");

    }


    @Test public void cyrillic_lowercase() {

        Print.test("Кириллица в нижнем регистре: ");
        List<String> cyrillic_lowercase = ASCII._get(Char_type.CYRILLIC_LOWERCASE);
        Print.result(cyrillic_lowercase + "\n");

    }

    @Test public void cyrillic_uppercase() {

        Print.test("Кириллица в верхнем регистре: ");
        List<String> cyrillic_uppercase = ASCII._get(Char_type.CYRILLIC_UPPERCASE);
        Print.result(cyrillic_uppercase + "\n");

    }

    @Test public void numbers() {

        Print.test("Цифры: ");
        List<String> numbers = ASCII._get(Char_type.NUMBERS);
        Print.result(numbers + "\n");

    }

    @Test public void special_characters() {

        Print.test("Специальные символы: ");
        List<String> special_characters_1 = ASCII._get(Char_type.SPECIAL_CHARACTERS_1);
        List<String> special_characters_2 = ASCII._get(Char_type.SPECIAL_CHARACTERS_2);
        List<String> special_characters_3 = ASCII._get(Char_type.SPECIAL_CHARACTERS_3);
        List<String> special_characters_4 = ASCII._get(Char_type.SPECIAL_CHARACTERS_4);

        List<String> super_special_characters = new ArrayList<>();
            super_special_characters.addAll(special_characters_1);
            super_special_characters.addAll(special_characters_2);
            super_special_characters.addAll(special_characters_3);
            super_special_characters.addAll(special_characters_4);

        Print.result(super_special_characters + "\n");

    }


    @Test public void get_selectively() {

        Integer value = 47;
        Print.test("Значение " + value + " соответствует знаку: ");
        Print.result("'" + ASCII._get_selectively(value) + "' \n");
    }


}