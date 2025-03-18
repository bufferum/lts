package lts;
import org.junit.Rule;
import org.junit.Test;
import lts.signs.Print;


/** @see lts.Stopwatch */
public class Test_stopwatch {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    @Test public void get_report() {

        Stopwatch stopwatch = new Stopwatch();

        stopwatch._start();

        // Program stop for 0.75 seconds
        try { Thread.sleep(750); }
        catch(InterruptedException e) { e.printStackTrace(); }

        Double difference = stopwatch._stop();

        Print.result("All time = " + difference + "s\n");

    }


}