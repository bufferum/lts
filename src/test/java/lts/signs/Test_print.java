package lts.signs;
import org.junit.Rule;
import org.junit.Test;
import lts.Reporter_tests;


/** @see lts.signs.Print */
public class Test_print {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    @Test public void inside_the_inner() {

        Print.result(
            "["
                + Print.test("TEST") +
            "]"
        );

    }

    @Test public void print_all() {

        Print.result("RESULT\n");
        Print.test("DEBUG\n");
        Print.error("ERROR\n");

    }


}