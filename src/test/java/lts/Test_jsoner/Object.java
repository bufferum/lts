package lts.Test_jsoner;
import org.junit.Test;

import lts.Jsoner;
import lts.Print;


/** @see lts.Jsoner */
public class Object {


    ////////// Methods //////////
    @Test
    public void OBJECT_to_json() {

        String json = Jsoner.OBJECT._to_json(new User());
        Print.test(json);

    }

    @Test
    public void OBJECT_from_json() {

        // to json
        String json = Jsoner.OBJECT._to_json(new User());
        Print.test("to_json:\n" + json + "\n\n");


        // to json
        User new_user = Jsoner.OBJECT._from_json(json, User.class);
        Print.result("to_json:\n"
                     + "name=" + new_user.getName() + "\n"
                     + "age=" + new_user.getAge() + "\n"
                     + "character_traits=" + new_user.getCharacter_traits() + "\n"
        );

    }


}