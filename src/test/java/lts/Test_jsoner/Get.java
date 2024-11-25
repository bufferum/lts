package lts.Test_jsoner;
import org.junit.Test;

import lts.Jsoner;
import lts.Print;


/** @see lts.Jsoner */
public class Get {


    ////////// Methods //////////
    @Test
    public void READ_value() {

        String json = Jsoner.OBJECT._to_json(new User());

        String result = Jsoner.GET._set_json(json)
                                   ._value("age");

        Print.test("Update-value:" + String.valueOf(result));

    }

    @Test
    public void READ_value_inner_array() {

        String json = Jsoner.OBJECT._to_json(new User());

        String result = Jsoner.GET._set_json(json)
                                   ._value_inner_array("studys", 1, "educational_institution");

        Print.test("Update-value_inner_array:" + String.valueOf(result));

    }

    @Test
    public void READ_array() {

        String json = Jsoner.OBJECT._to_json(new User());

        String result = Jsoner.GET._set_json(json)
                                   ._array("character_traits", 2);

        Print.test("Update-array:" + String.valueOf(result));

    }


}