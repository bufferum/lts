package lts;
import org.junit.Rule;
import org.junit.Test;
import lts.signs.Print;


/** @see lts.Terminal */
public class Test_terminal {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    @Test public void terminal() {

        Terminal terminal = new Terminal(
            "cd /",
            "ls -la"
        );

        Print.result(terminal._get_result() + "\n");

    }


}