package lts.files.jsoner;
import org.junit.Rule;
import org.junit.Test;
import lts.Reporter_tests;
import lts.Test_USER;
import lts.files.Jsoner;
import lts.signs.Print;


/** @see lts.files.Jsoner */
public class Test_jsoner_read {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();
    private static final String json = Jsoner.CREATE._to_json(new Test_USER());


    ////////// Methods //////////
    @Test public void value() {

        String result = Jsoner.READ._set_json(json)
                                   ._value("age");

        Print.test("Update-value:" + String.valueOf(result));

    }

    @Test public void value_inner_array() {

        String result = Jsoner.READ._set_json(json)
                                   ._value_inner_array("studys", 1, "educational_institution");

        Print.test("Update-value_inner_array:" + String.valueOf(result));

    }

    @Test public void array() {

        String result = Jsoner.READ._set_json(json)
                                   ._array("character_traits", 2);

        Print.test("Update-array:" + result);

    }


}