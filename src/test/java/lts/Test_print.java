package lts;
import org.junit.Test;


/** @see lts.Print */
public class Test_print {


    ////////// Methods //////////
    @Test
    public void inside_the_inner() {

        Print.result(
            "["
                + Print.test("TEST") +
            "]"
        );

    }

    @Test
    public void print_all() {

        Print.result("RESULT\n");
        Print.test("DEBUG\n");
        Print.error("ERROR\n");

    }

    @Test
    public void print_disable_printer() {

        Print.test("[print_disable_printer] - its work printer 1\n");

        Print.printer_disable();

        Print.test("[print_disable_printer] - its work printer 2\n");

        Print.printer_enable();

        Print.test("[print_disable_printer] - its work printer 3\n\n");

        Print.result("[print_disable_printer] - its work loger:\n" + Print.get_log());

    }

    @Test
    public void print_disable_loger() {

        Print.test("[print_disable_loger] - its work printer 1\n");

        Print.logger_disable();

        Print.test("[print_disable_loger] - its work printer 2\n");

        Print.logger_enable();

        Print.test("[print_disable_loger] - its work printer 3\n\n");

        Print.result("[print_disable_loger] - its work loger:\n" + Print.get_log());

    }


}