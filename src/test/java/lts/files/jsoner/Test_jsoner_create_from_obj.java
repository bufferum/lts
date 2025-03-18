package lts.files.jsoner;
import org.junit.Rule;
import org.junit.Test;
import lts.Reporter_tests;
import lts.Test_USER;
import lts.files.Jsoner;
import lts.signs.Print;


/** @see lts.files.Jsoner */
public class Test_jsoner_create_from_obj {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    @Test public void to_json() {

        String json = Jsoner.CREATE._to_json(new Test_USER());
        Print.test(json);

    }

    @Test public void from_json() {

        // to json
        String json = Jsoner.CREATE._to_json(new Test_USER());
        Print.test("to_json:\n" + json + "\n\n");


        // to json
        Test_USER new_user = Jsoner.CREATE._from_json(json, Test_USER.class);
        Print.result("to_json:\n"
                     + "name=" + new_user.getName() + "\n"
                     + "age=" + new_user.getAge() + "\n"
                     + "character_traits=" + new_user.getCharacter_traits() + "\n"
        );

    }


}