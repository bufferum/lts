package lts.signs;

/**
 * <h4>Printer.</h4>
 *
 * @apiNote Do not use such an entry:
 *          {@code Print.result("[" + Print.test("TEST") + "]");}
 *
 * @patterns Singleton, Momento
 * @version 2.0
 * @author bufferum
 */
public class Print {


    ////////// Variables //////////
    private static Print print;
    private static boolean key_print;


    ////////// Constructors //////////
    private Print() { printer_enable(); }


    ////////// Methods //////////
    /** <p>Turning on the printer. For example, in loops, it is sometimes necessary to disable the output while saving the code.</p> */
    public static void printer_enable() { key_print = true; }

    /** <p>Disabling the printer. For example, in loops, it is sometimes necessary to disable the output while saving the code.</p> */
    public static void printer_disable() { key_print = false; }

    public static String result(Object cmd) { return condition(cmd, Colors.YELLOW); }
    public static String test(Object cmd)  { return condition(cmd, Colors.BLUE); }
    public static String error(Object cmd)  { return condition(cmd, Colors.BG_RED); }

    private static String condition(Object cmd, String color) {

        // For singleton
        if(print == null) {

            print = new Print();
        }

        String result =
            Colors.RESET +
            color +
            cmd +
            Colors.RESET;

        if(key_print) {

            System.out.print(result);
        }

        return result;
    }


}