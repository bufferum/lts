package lts;


/**
 * <h4>Simple stopwatch.</h4>
 *
 * @version 2.0
 * @author bufferum
 */
public class Stopwatch {


    ////////// Variables //////////
    private long start;


    ////////// Methods //////////
    public void _start() {

        start = System.currentTimeMillis();

    }

    public Double _stop() {

        return (Double) ((System.currentTimeMillis() - start) / 1000.00);
    }


}