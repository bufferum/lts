package lts.signs;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import lts.Reporter_tests;


/** @see lts.signs.Random */
public class Test_random {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    @Test public void random() {

        Double min = 22.4455;
        Double max = 50.0;

        for(int i = 1; i <= 10; i++) {

            Integer result = Random._random(min, max)._to_int();

            Print.test(i + ". " + result + "\n");

        }

    }

    @Test public void random_object() {

        Double num = 22.4455;
        String text = "hello world";
        File file = new File("src/test/resources/Jsoner/unknown.json");

        List<Object> list_obj = new ArrayList<>();
            list_obj.add(num);
            list_obj.add(file);
            list_obj.add(text);

        Object result = Random._random_object(list_obj);

        Print.result(result + "\n");

    }


}