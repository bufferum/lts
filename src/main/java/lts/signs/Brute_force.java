package lts.signs;
import java.util.ArrayList;
import java.util.List;


/**
 * <h4>Brute_force - this is a class for iterating through all the variants of an array of characters.</h4>
 *
 * @version 2.0
 * @author bufferum
 */
public class Brute_force {


    ////////// Variables //////////
    /** The result is in the form of a list */
    private List<List<String>> result;


    ////////// Constructors //////////
    @SuppressWarnings("unused")
    private Brute_force() { }
    public Brute_force(Integer min_length, Integer max_length, List<String> symbols) {

        Integer min_length_begin = min_length;
        Integer max_length_begin = max_length;
        result = new ArrayList<>();

        // Getting all the options from the minimum to the maximum character set length
        for(int current_step = 0; current_step < max_length; current_step++, min_length++) {

            List<String> result_step = new ArrayList<>();

            if(current_step == 0) {

                for(String symbol : symbols) {

                    result_step.add(symbol);

                }

            }
            else {

                List<String> result_step_last = result.getLast();

                for(String symbol : symbols) {

                    for(String variation : result_step_last) {

                        result_step.add(variation + symbol);

                    }

                }

            }

            result.add(result_step);

        }

        // Cleaning the result from unnecessary sets - according to the minimum set length
        result = result.subList(min_length_begin - 1, max_length_begin);

    }


    ////////// Methods //////////
    public List<List<String>> _get_result() { return result; }


}