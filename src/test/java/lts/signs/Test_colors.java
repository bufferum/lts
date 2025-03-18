package lts.signs;
import org.junit.Rule;
import org.junit.Test;
import lts.Reporter_tests;


/** @see lts.signs.Colors */
public class Test_colors {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    @Test public void get_all_colors() {

        Print.result(Colors.BG_RED + Colors.WHITE + "THE TEST CLASS COLORS\n");

    }


}