package lts;
import lts.algorithms.Register_thread_id;
import lts.signs.Colors;
import lts.signs.Emoji;
import lts.signs.Print;


/**
 * <h4>This class is used to visualize the completion of a task in stages.</h4>
 *
 * <p>Create an instance of this class, pass an array with descriptions, and send a signal using the {@code _send_signal} method.</p>
 *
 * <h4>Attention!!!</h4>
 * <p>There should be exactly as many signals as there are elements in the {@code description} array.</p>
 *
 * @version 2.0
 * @author bufferum
 */
public class Progress_bar {


    ////////// Variables //////////
    private final Integer LENGTH_SCALE = 50;
    private final String[] SYMBOLS = { "/", "-", "\\", "|" };

    private String[] descriptions;
    private Integer current_signal = 0;

    /** Responsible for stopping the endless flow of the indicator */
    private Boolean key_indicator = true;

    /** It is needed to save the maximum length of the action description */
    private Integer buffer_length = 0;

    private Thread thread;


    ////////// Methods //////////
    public void _start(Runnable runnable, String[] descriptions) {

        this.descriptions = descriptions;
        current_signal = 0;

        Thread thread_stream_indicator = new Thread(stream_indicator);
            thread_stream_indicator.start();

        thread = new Thread(runnable);
        thread.setName("Progress_bar_" + new Register_thread_id()._get_thread_id());
        thread.start();

    }

    public void _send_signal() {

        try {

            this.current_signal++;

        }
        catch(Exception e) {
            key_indicator = false;
        }

    }

    /**
     * <p>This function completely rewrites the line in time with
     * the indicator - every 0.3 seconds.</p>
     * <p>If {@code get_percent()} has sent a value that differs from
     * the previous one, then the progress bar will be significantly overwritten.</p>
     */
    private Runnable stream_indicator = () -> {

        while(key_indicator) {

            Integer count = get_percent();
            String result = Colors.BG_YELLOW + "|" + Colors.RESET;

            for(int i = 0; i < count; i++) {
                result += Colors.BG_YELLOW + "#" + Colors.RESET;
            }

            for(int i = count; i < LENGTH_SCALE; i++) {
                result += Colors.YELLOW + "_" + Colors.RESET;
            }

            result += Colors.BG_YELLOW + "|" + Colors.RESET + Colors.YELLOW;

            if(current_signal == descriptions.length) {

                key_indicator = false;

                String result_super = "";
                for(int i = 0; i < LENGTH_SCALE; i++) {
                    result_super += "#";
                }

                Print.result("\r" + Emoji.CHECK_MARK + Colors.BG_YELLOW + "|" + result_super + "|" + Colors.RESET + Colors.YELLOW + get_whitespaces() + "\n");

            }
            else {

                for(int i = 0; i < SYMBOLS.length && current_signal < descriptions.length; i++) {

                    Print.result("\r" + SYMBOLS[i] + " " + result + " - " + descriptions[current_signal] + get_whitespaces());

                    try { Thread.sleep(300); }
                    catch (InterruptedException e) { e.printStackTrace(); }

                }

            }

        }

    };

    /**
     * <h4>Task:</h4>
     * <p>Express 20% on a scale
     *
     * <h4>Given:</h4>
     * <p>LENGTH_SCALE = 50
     * <p>tasks.length = 5
     * <p>current_signal = 1
     *
     * <h4>Decision:</h4>
     * <p>x = round((1/5) * 50) = 10
     *
     * <p>    x - the number of characters(loaded)
     * <p>round - the rounding function
     */
    private int get_percent() {

        return (int) Math.round((double) current_signal / descriptions.length * LENGTH_SCALE);
    }

    /** Removing the description of actions after the scale */
    private String get_whitespaces() {

        String whitespaces = "";
        Integer count = 0;
        Integer this_length = 0;

        // Memorizing which command is the longest
        if(current_signal < descriptions.length) {

            this_length = descriptions[current_signal].length();

            if(buffer_length < this_length) {
                buffer_length = this_length;
            }
            else {
                count = (buffer_length - this_length);
            }

            buffer_length = this_length;

        }
        else {
            count = buffer_length + (" - ".length());
        }

        for(int i = 0; i < count; i++) {

            whitespaces += " ";

        }

        return whitespaces;
    }


}