package lts.signs;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import lts.Reporter_tests;
import lts.Test_USER;


/** @see lts.signs.Converter */
public class Test_converter {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    @Test public void to_array() {

        Test_USER user_1 = new Test_USER();
            user_1.setName("User_1");
            user_1.setAge(10);
            user_1.setCharacter_traits(new String[] { "trait_1" });

        Test_USER user_2 = new Test_USER();
            user_2.setName("User_2");
            user_2.setAge(20);
            user_2.setCharacter_traits(new String[] { "trait_1", "trait_2" });

        Test_USER user_3 = new Test_USER();
            user_3.setName("User_3");
            user_3.setAge(30);
            user_3.setCharacter_traits(new String[] { "trait_1", "trait_2", "trait_3" });

        List<Test_USER> users_list = new ArrayList<>();
            users_list.add(user_1);
            users_list.add(user_2);
            users_list.add(user_3);

        Test_USER[] users_array = Converter._to_array(users_list);

        for(int i = 0; i < users_array.length; i++) {

            Test_USER user = users_array[i];
            Print.result("User " + ++i + "\n");
            i--;
            Print.result("name=" + user.getName() + "\n");
            Print.result("age=" + user.getAge() + "\n");
            Print.result("age=" + user.getCharacter_traits() + "\n\n\n");

        }

    }

    @Test public void to_list() {

        Test_USER user_1 = new Test_USER();
            user_1.setName("User_1");
            user_1.setAge(10);
            user_1.setCharacter_traits(new String[] { "trait_1" });

        Test_USER user_2 = new Test_USER();
            user_2.setName("User_2");
            user_2.setAge(20);
            user_2.setCharacter_traits(new String[] { "trait_1", "trait_2" });

        Test_USER user_3 = new Test_USER();
            user_3.setName("User_3");
            user_3.setAge(30);
            user_3.setCharacter_traits(new String[] { "trait_1", "trait_2", "trait_3" });

        Test_USER[] users_array = new Test_USER[3];
            users_array[0] = user_1;
            users_array[1] = user_2;
            users_array[2] = user_3;

        List<Test_USER> users_list = Converter._to_list(users_array);

        for(int i = 0; i < users_list.size(); i++) {

            Test_USER user = users_list.get(i);
            Print.result("User " + ++i + "\n");
            i--;
            Print.result("name=" + user.getName() + "\n");
            Print.result("age=" + user.getAge() + "\n");
            Print.result("age=" + user.getCharacter_traits() + "\n\n\n");

        }

    }


}