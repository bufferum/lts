package lts;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import lts.signs.Print;


/**
 * <h4>This class is used so that at the beginning of each test,
 * the name of the class and function that the test is currently
 * running is declared.</h4>
 *
 * @version 2.0
 * @author bufferum
 */
public class Reporter_tests extends TestWatcher {


    ////////// Methods //////////
    @Override
    protected void starting(Description description) {

        String test_name = description.getClassName() + "." + description.getMethodName() + "()";

        Print.result("\n========== " + test_name + " ==========\n");

    }


}