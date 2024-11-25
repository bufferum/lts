package lts.Test_jsoner;
import org.junit.Test;

import lts.Jsoner;
import lts.Print;


/** @see lts.Jsoner */
public class Set {

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


    ////////// Methods //////////
    @Test
    public void EDIT_value() {

        String json = Jsoner.OBJECT._to_json(new User());

        Print.test("Old_json:\n" + json + "\n\n");

        json = Jsoner.SET._set_json(json)
                         ._value("age", 30)
                         ._get_json();

        json = Jsoner.FORMAT._structuring(json);

        Print.test("Update_json:\n" + json);

    }

    @Test
    public void EDIT_value_inner_array() {

        String json = Jsoner.OBJECT._to_json(new User());

        Print.test("Old_json:\n" + json + "\n\n");

        json = Jsoner.SET._set_json(json)
                         ._value_inner_array("studys", 1, "educational_institution", "NEW_UNIVERSITY_2")
                         ._get_json();

        Print.test("Update_value_inner_array:" + json);

    }

    @Test
    public void EDIT_array() {

        String json = Jsoner.OBJECT._to_json(new User());

        Print.test("Old_json:\n" + json + "\n\n");

        json = Jsoner.SET._set_json(json)
                         ._array("character_traits", 2, "Clever")
                         ._get_json();

        Print.test("Update_array:" + json);

    }


}