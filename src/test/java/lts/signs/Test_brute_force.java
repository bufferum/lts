package lts.signs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import lts.Reporter_tests;
import lts.Stopwatch;
import lts.signs.ASCII.Char_type;


/** @see lts.signs.Brute_force */
public class Test_brute_force {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Variables //////////
    public Integer min_length = 1;
    public Integer max_length = 3;


    ////////// Methods //////////
    // @Test
    public void get_result() {

        List<String> numbers = ASCII._get(Char_type.NUMBERS);
        List<String> latin_lowercase = ASCII._get(Char_type.LATIN_LOWERCASE);
        List<String> latin_uppercase = new ArrayList<>();
            latin_uppercase.add("A");
            latin_uppercase.add("B");
            latin_uppercase.add("C");
            latin_uppercase.add("D");
            latin_uppercase.add("E");
        List<String> symbols = new ArrayList<>();
                symbols.addAll(numbers);
                symbols.addAll(latin_lowercase);
                symbols.addAll(latin_uppercase);
        Brute_force brute_force = new Brute_force(min_length, max_length, symbols);
        Iterator<List<String>> iterator = brute_force._get_result().iterator();
        Integer i = 0;

        while(iterator.hasNext()) {

            i++;

            int count = 0;
            Print.result("\nStep_" + i + "\n");

            Iterator<String> iterator2 = iterator.next().iterator();

            while(iterator2.hasNext()) {

                count++;
                Print.result(iterator2.next() + "\n");

            }

            Print.result("Total elements: " + count + "\n");

        }

    }

    @Test public void get_unique() {

        Stopwatch stopwatch = new Stopwatch();
            stopwatch._start();
        List<String> symbols = ASCII._get(Char_type.NUMBERS);
        Brute_force brute_force = new Brute_force(min_length, max_length, symbols);
        List<Integer> list_num = new ArrayList<>();
        Iterator<List<String>> iterator = brute_force._get_result().iterator();
        Integer i = 0;

        while(iterator.hasNext()) {

            int count = 0;
            Iterator<String> iterator2 = iterator.next().iterator();

            i++;
            Print.result("\nStep_" + i + "\n");

            while(iterator2.hasNext()) {

                count++;
                String num = iterator2.next();
                // Print.result(num + "\n");
                list_num.add(Integer.valueOf(num));

            }

            Print.result("Total elements: " + count + "\n");

        }


        Print.result("\nTotal elements to list_num: " + list_num.size() + "\n");
        Print.result("\nCalculating unique values:\n");

        List<Integer> list_num_unique = new ArrayList<>();

        for(Integer x : list_num) {

            if(find_to_array(x, list_num_unique)) {
                list_num_unique.add(x);
            }

        }

        Print.result("Total elements: " + list_num_unique.size() + "\n");

        Print.test("All time = " + stopwatch._stop() + "s");

        // Sorting
        Collections.sort(list_num_unique);

    }

    private boolean find_to_array(Integer find, List<Integer> list) {

        for(int i = 0; i < list.size(); i++) {

            if(list.get(i) == find) {

                return false;
            }

        }

        return true;
    }


}