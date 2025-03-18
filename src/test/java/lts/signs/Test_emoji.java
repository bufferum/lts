package lts.signs;
import org.junit.Rule;
import org.junit.Test;
import lts.Reporter_tests;


/** @see lts.signs.Emoji */
public class Test_emoji {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    @Test public void get_all_emoji() {

        Print.result("|" + Emoji.CHECK_MARK + "|\n");

    }


}