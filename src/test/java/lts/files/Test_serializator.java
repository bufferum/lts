package lts.files;
import java.io.File;
import org.junit.Rule;
import org.junit.Test;
import lts.Reporter_tests;
import lts.Test_USER;
import lts.signs.Print;


/** @see lts.files.Serializator */
public class Test_serializator {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();
    private static final String RESOURCES = "src/test/resources/Test_serializator/";


    ////////// Methods //////////
    @Test public void serialize() throws Exception {

        final File file = new File(RESOURCES + "test_serializator");
        Test_USER user = new Test_USER();
        Serializator._serialize(file, user);

    }

    @Test public void deserialize() throws Exception {

        final File file = new File(RESOURCES + "test_serializator");
        Test_USER user_clone = null;
            user_clone = (Test_USER) Serializator._deserialize(file);

        Print.test("User name: " + user_clone.getName());

    }


}