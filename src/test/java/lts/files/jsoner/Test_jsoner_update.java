package lts.files.jsoner;
import org.junit.Rule;
import org.junit.Test;
import lts.Reporter_tests;
import lts.Test_USER;
import lts.files.Jsoner;
import lts.signs.Print;


/** @see lts.files.Jsoner */
public class Test_jsoner_update {


    // {
    //     "name": "bufferum",
    //     "age": 20,
    //     "studys": [
    //       {
    //         "educational_institution": "MGU_1",
    //         "year_of_completion": 2020
    //       },
    //       {
    //         "educational_institution": "MGU_2",
    //         "year_of_completion": 2024
    //       }
    //     ],
    //     "character_traits": [
    //       "Kind",
    //       "Wit",
    //       "Brave"
    //     ]
    // }


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    @Test public void value() {

        String json = Jsoner.CREATE._to_json(new Test_USER());

        Print.test("Old_json:\n" + json + "\n\n");

        json = Jsoner.UPDATE._set_json(json)
                         ._value("age", 30)
                         ._get_json();

        json = Jsoner.FORMAT._structuring(json);

        Print.test("Update_json:\n" + json);

    }

    @Test public void value_inner_array() {

        String json = Jsoner.CREATE._to_json(new Test_USER());

        Print.test("Old_json:\n" + json + "\n\n");

        json = Jsoner.UPDATE._set_json(json)
                         ._value_inner_array("studys", 1, "educational_institution", "NEW_UNIVERSITY_2")
                         ._get_json();

        Print.test("Update_value_inner_array:" + json);

    }

    @Test public void array() {

        String json = Jsoner.CREATE._to_json(new Test_USER());

        Print.test("Old_json:\n" + json + "\n\n");

        json = Jsoner.UPDATE._set_json(json)
                         ._array("character_traits", 2, "Clever")
                         ._get_json();

        Print.test("Update_array:" + json);

    }


}