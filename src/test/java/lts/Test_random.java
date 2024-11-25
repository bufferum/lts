package lts;
import org.junit.Test;


/** @see lts.Random */
public class Test_random {


    ////////// Methods //////////
    @Test
    public void random() {

        for(int i = 1; i <= 10; i++) {

            Print.test(i + ". " + Random._random(10, 30)._to_int() + "\n");

        }

    }


}