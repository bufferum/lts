package lts;
import org.junit.Rule;
import org.junit.Test;


/** @see lts.signs.Progress_bar */
public class Test_progress_bar {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    @Test public void loading_progress_bar() {

        String[] descriptions = {
            "Enabling the program!",
            "Simulation of operation 1",
            "Simulation of operation 2",
            "Simulation of operation 3",
            "Disabling the program!"
        };
        Progress_bar progress_bar = new Progress_bar();
        progress_bar._start(new Runnable() {

            @Override
            public void run() {

                // Task_1
                try { Thread.sleep(2000); }
                catch(InterruptedException e) { e.printStackTrace(); }
                progress_bar._send_signal();

                // Task_2
                try { Thread.sleep(2000); }
                catch(InterruptedException e) { e.printStackTrace(); }
                progress_bar._send_signal();

                // Task_3
                try { Thread.sleep(2000); }
                catch(InterruptedException e) { e.printStackTrace(); }
                progress_bar._send_signal();

                // Task_4
                try { Thread.sleep(2000); }
                catch(InterruptedException e) { e.printStackTrace(); }
                progress_bar._send_signal();

                // Task_5
                try { Thread.sleep(2000); }
                catch(InterruptedException e) { e.printStackTrace(); }
                progress_bar._send_signal();

            }


        }, descriptions);

    }


}