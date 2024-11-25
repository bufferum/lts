package lts;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;


/**
 * A class that provides methods for getting random values
 *
 * @patterns Singleton
 * @version 1.0
 * @author bufferum
 */
@DefaultQualifier(NonNull.class)
public class Random {


    ////////// Variables //////////
    private static boolean flag_int = false;


    ////////// Constructors //////////
    private Random() { }


    ////////// Methods //////////
    public static Random_return _random(Double MIN, Double MAX) {

        flag_int = false;
        return random(MIN, MAX);
    }

    public static Random_return _random(Integer MIN, Integer MAX) {

        flag_int = true;
        return random(MIN.doubleValue(), MAX.doubleValue());
    }

    private static Random_return random(Double MIN, Double MAX) {

        MAX -= MIN;
        return new Random_return((Math.random() * ++MAX) + MIN);
    }


    ////////// Class //////////
    public static class Random_return {


        ////////// Variables //////////
        @Nullable private Double var;


        ////////// Constructors //////////
        private Random_return() { }
        private Random_return(Double var) {

            this.var = var;
        }


        ////////// Methods //////////
        public Integer _to_int() {

            return this.var.intValue();
        }

        public Double _to_double() {

            if(flag_int) {

                Integer i = (int) Math.round(this.var);
                return i.doubleValue();
            }
            else {

                return this.var;
            }

        }


    }


}