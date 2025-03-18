package lts.signs;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;


/**
 * <h4>This class converts a sheet into an array of the same template type
 * and vice versa.</h4>
 * <p>Example: {@code List<TYPE>} <=> {@code TYPE[]}<hr>
 *
 * @patterns Singleton
 * @version 2.0
 * @author bufferum
 */
@DefaultQualifier(NonNull.class)
public class Converter {


    ////////// Constructors //////////
    private Converter() { }


    ////////// Methods //////////
    @SuppressWarnings("unchecked")
    public static <TYPE> TYPE[] _to_array(List<TYPE> list) {

        TYPE[] array = (TYPE[]) Array.newInstance(list.get(0).getClass(), list.size());

        for(int i = 0; i < list.size(); i++) {

            array[i] = list.get(i);

        }

        return (TYPE[]) array;
    }

    @SuppressWarnings("unchecked")
    public static <TYPE> List<TYPE> _to_list(TYPE... array) {

        List<TYPE> list = new ArrayList<>();

        for(TYPE a : array) {

            list.add((TYPE) a);

        }

        return (List<TYPE>) list;
    }


    public static String[] _convert(String... array) {

        if(array == null) {

            Print.printer_disable();
            throw new NullPointerException(Print.error("The array is empty!!!"));
        }

        String[] result = new String[array.length];

        for(int i = 0; i < array.length; i++) {

            result[i] = array[i];

        }

        return result;
    }

    public static Integer[] _convert(int... array) {

        if(array == null) {

            Print.printer_disable();
            throw new NullPointerException(Print.error("The array is empty!!!"));
        }

        Integer[] result = new Integer[array.length];

        for(int i = 0; i < array.length; i++) {

            result[i] = array[i];

        }

        return result;
    }

    public static Byte[] _convert(byte... array) {

        if(array == null) {

            Print.printer_disable();
            throw new NullPointerException(Print.error("The array is empty!!!"));
        }

        Byte[] result = new Byte[array.length];

        for(int i = 0; i < array.length; i++) {

            result[i] = array[i];

        }

        return result;
    }

    public static Double[] _convert(double... array) {

        if(array == null) {

            Print.printer_disable();
            throw new NullPointerException(Print.error("The array is empty!!!"));
        }

        Double[] result = new Double[array.length];

        for(int i = 0; i < array.length; i++) {

            result[i] = array[i];

        }

        return result;
    }

    public static Boolean[] _convert(boolean... array) {

        if(array == null) {

            Print.printer_disable();
            throw new NullPointerException(Print.error("The array is empty!!!"));
        }

        Boolean[] result = new Boolean[array.length];

        for(int i = 0; i < array.length; i++) {

            result[i] = array[i];

        }

        return result;
    }

    public static Character[] _convert(char... array) {

        if(array == null) {

            Print.printer_disable();
            throw new NullPointerException(Print.error("The array is empty!!!"));
        }

        Character[] result = new Character[array.length];

        for(int i = 0; i < array.length; i++) {

            result[i] = array[i];

        }

        return result;
    }

    public static Float[] _convert(float... array) {

        if(array == null) {

            Print.printer_disable();
            throw new NullPointerException(Print.error("The array is empty!!!"));
        }

        Float[] result = new Float[array.length];

        for(int i = 0; i < array.length; i++) {

            result[i] = array[i];

        }

        return result;
    }

    public static Short[] _convert(short... array) {

        if(array == null) {

            Print.printer_disable();
            throw new NullPointerException(Print.error("The array is empty!!!"));
        }

        Short[] result = new Short[array.length];

        for(int i = 0; i < array.length; i++) {

            result[i] = array[i];

        }

        return result;
    }

    public static Long[] _convert(long... array) {

        if(array == null) {

            Print.printer_disable();
            throw new NullPointerException(Print.error("The array is empty!!!"));
        }

        Long[] result = new Long[array.length];

        for(int i = 0; i < array.length; i++) {

            result[i] = array[i];

        }

        return result;
    }


    public static int[] _to_primitive(Integer... array) {

        if(array == null) {

            Print.printer_disable();
            throw new NullPointerException(Print.error("The array is empty!!!"));
        }

        int[] primitive_array = new int[array.length];

        for(int i = 0; i < array.length; i++) {

            primitive_array[i] = array[i];

        }

        return primitive_array;
    }

    public static byte[] _to_primitive(Byte... array) {

        if(array == null) {

            Print.printer_disable();
            throw new NullPointerException(Print.error("The array is empty!!!"));
        }

        byte[] primitive_array = new byte[array.length];

        for(int i = 0; i < array.length; i++) {

            primitive_array[i] = array[i];

        }

        return primitive_array;
    }

    public static double[] _to_primitive(Double... array) {

        if(array == null) {

            Print.printer_disable();
            throw new NullPointerException(Print.error("The array is empty!!!"));
        }

        double[] primitive_array = new double[array.length];

        for(int i = 0; i < array.length; i++) {

            primitive_array[i] = array[i];

        }

        return primitive_array;
    }

    public static boolean[] _to_primitive(Boolean... array) {

        if(array == null) {

            Print.printer_disable();
            throw new NullPointerException(Print.error("The array is empty!!!"));
        }

        boolean[] primitive_array = new boolean[array.length];

        for(int i = 0; i < array.length; i++) {

            primitive_array[i] = array[i];

        }

        return primitive_array;
    }

    public static char[] _to_primitive(Character... array) {

        if(array == null) {

            Print.printer_disable();
            throw new NullPointerException(Print.error("The array is empty!!!"));
        }

        char[] primitive_array = new char[array.length];

        for(int i = 0; i < array.length; i++) {

            primitive_array[i] = array[i];

        }

        return primitive_array;
    }

    public static float[] _to_primitive(Float... array) {

        if(array == null) {

            Print.printer_disable();
            throw new NullPointerException(Print.error("The array is empty!!!"));
        }

        float[] primitive_array = new float[array.length];

        for(int i = 0; i < array.length; i++) {

            primitive_array[i] = array[i];

        }

        return primitive_array;
    }

    public static short[] _to_primitive(Short... array) {

        if(array == null) {

            Print.printer_disable();
            throw new NullPointerException(Print.error("The array is empty!!!"));
        }

        short[] primitive_array = new short[array.length];

        for(int i = 0; i < array.length; i++) {

            primitive_array[i] = array[i];

        }

        return primitive_array;
    }

    public static long[] _to_primitive(Long... array) {

        if(array == null) {

            Print.printer_disable();
            throw new NullPointerException(Print.error("The array is empty!!!"));
        }

        long[] primitive_array = new long[array.length];

        for(int i = 0; i < array.length; i++) {

            primitive_array[i] = array[i];

        }

        return primitive_array;
    }


}