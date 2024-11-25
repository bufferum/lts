package lts;
import java.io.File;
import org.junit.Test;


/** @see lts.Serializator */
public class Test_serializator {


    ////////// Methods //////////
    @Test
    public void serialize() throws Exception {

        final String RESOURCES = "src/test/resources/Serializator/";
        final File file = new File(RESOURCES + "test_serializator");

        User user = new User();
        Serializator._serialize(file, user);

    }

    @Test
    public void deserialize() throws Exception {

        final String RESOURCES = "src/test/resources/Serializator/";
        final File file = new File(RESOURCES + "test_serializator");
        User user_clone = null;

        user_clone = (User) Serializator._deserialize(file);

        Print.test("User name: " + user_clone.getName());

    }


}