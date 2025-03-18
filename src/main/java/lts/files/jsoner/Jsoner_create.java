package lts.files.jsoner;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


/** @see lts.files.Jsoner */
@DefaultQualifier(NonNull.class)
public class Jsoner_create {


    ////////// Variables //////////
    @Nullable private static JsonObject json_object;
    @Nullable private static JsonArray json_array;
    @Nullable private static Jsoner_map_object jsoner_map_object;
    @Nullable private static Jsoner_map_array jsoner_map_array;
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    ////////// Constructors //////////
    public Jsoner_create() { }


    ////////// Methods //////////

    ////////// CREATE FROM OBJECT //////////
    /** To convert data into a json (String) structure */
    public String _to_json(Object object) {

        return gson.toJson(object);
    }

    /** To convert a json structure into a collection for further iteration */
    public <T> T _from_json(String json, Class<T> class_of_T) {

        return gson.fromJson(json, class_of_T);
    }

    ////////// CREATE FROM VARIABLES //////////
    /**
     * <p>Use such a record:
     * <pre>
     * {@code Iterator<Map.Entry<String, String>> iterator = Jsoner.CREATE_FROM_VAR._from_json((String) json); }
     * while(iterator.hasNext()) {
     *
     *     Entry<String, String> entry = iterator.next();
     *
     *     Print.result(
     *         "key=" + entry.getKey() + " " +
     *         "value=" + entry.getValue() + "\n"
     *     );
     *
     * }
     * </pre>
     */
    public Iterator<Map.Entry<String, String>> _from_json(String json) {

        Type type = new TypeToken<Map<String, String>>() {}.getType();
        Map<String, String> result = gson.fromJson(json, type);

        return result.entrySet().iterator();
    }

    /**
     * <p>Is used to convert (String) json.
     * <p>Which was filled due to one or more number of calls to the
     * {@code _set(key, value)} method.
     */
    public String _to_json() {

        String result = "";

        if(jsoner_map_array != null) {

            result = gson.toJson(json_array);
        }
        else {

            result = gson.toJson(json_object);
        }

        json_object = null;
        json_array = null;
        jsoner_map_object = null;
        jsoner_map_array = null;

        return result;
    }

    /**
     * <p>Converts or complements (if this method has already been called)
     * the current json_object to json_array in order to retrieve
     * each json_object by id in the future.
     *
     * <p>It is used, for example, in cycles. When the data is similar,
     * but they need to be collected. And Mar will not allow you to collect
     * the same keys. Therefore, the json tree grows, a new array element
     * appears in which the key can be duplicated.
     * <p>Here is the result of this method, where there are similar keys:
     *
     * <pre>
     * [
     *  {
     *      "key_1": "value_1",
     *      "key_2": "value_2",
     *      "key_3": "value_3"
     *  },
     *  {
     *      "key_1": "value_4",
     *      "key_2": "value_5",
     *      "key_3": "value_6"
     *  }
     * ]
     * </pre>
     *
     */
    @Deprecated
    public Jsoner_map_array _wrap_array() {

        if(jsoner_map_array == null) {

            jsoner_map_array = new Jsoner_map_array();
        }

        json_array.add(json_object);

        json_object = new JsonObject();

        return jsoner_map_array;
    }

    /**
     * <p>To convert data into a json (String) structure.
     * This method accept an object. It will then be converted
     * to a string. Therefore, it is recommended to enter a SPECIFIC
     * value, otherwise the object falls under the action of
     * toString and turns into a reference.
     */
    public Jsoner_map_object _set(Object key, Object value) throws Exception {

        if(json_object == null) {

            json_object = new JsonObject();
            json_array = new JsonArray();
            jsoner_map_object = new Jsoner_map_object();

        }

        json_object.addProperty(String.valueOf(key), String.valueOf(value));

        return jsoner_map_object;
    }


    ////////// Class //////////
    public static class Jsoner_map_object {


        ////////// Constructors //////////
        private Jsoner_map_object() { }


        ////////// Methods //////////
        /**
         * <p>To convert data into a json (String) structure.
         * This method accept an object. It will then be converted
         * to a string. Therefore, it is recommended to enter a SPECIFIC
         * value, otherwise the object falls under the action of
         * toString and turns into a reference.
         */
        public Jsoner_map_object _set(Object key, Object value) {

            json_object.addProperty(String.valueOf(key), String.valueOf(value));

            return jsoner_map_object;
        }

        /**
         * <p>Is used to convert (String) json.
         * <p>Which was filled due to one or more number of calls to the
         * {@code _set(key, value)} method.
         */
        public String _to_json() {

            String result = gson.toJson(json_object);
            json_object = null;

            return result;
        }


    }

    public static class Jsoner_map_array {


        ////////// Constructors //////////
        private Jsoner_map_array() { }


        ////////// Methods //////////
        /**
         * <p>To convert data into a json (String) structure.
         * This method accept an object. It will then be converted
         * to a string. Therefore, it is recommended to enter a SPECIFIC
         * value, otherwise the object falls under the action of
         * toString and turns into a reference.
         */
        public Jsoner_map_array _set(Object key, Object value) {

            json_object.addProperty(String.valueOf(key), String.valueOf(value));

            return jsoner_map_array;
        }

        /**
         * <p>Converts or complements (if this method has already been called)
         * the current json_object to json_array in order to retrieve
         * each json_object by id in the future.
         *
         * <p>It is used, for example, in cycles. When the data is similar,
         * but they need to be collected. And Mar will not allow you to collect
         * the same keys. Therefore, the json tree grows, a new array element
         * appears in which the key can be duplicated.
         * <p>Here is the result of this method, where there are similar keys:
         *
         * <pre>
         * [
         *  {
         *      "key_1": "value_1",
         *      "key_2": "value_2",
         *      "key_3": "value_3"
         *  },
         *  {
         *      "key_1": "value_1",
         *      "key_2": "value_2",
         *      "key_3": "value_3"
         *  }
         * ]
         * </pre>
         *
         */
        @Deprecated
        public Jsoner_map_array _wrap_array() {

            if(jsoner_map_array == null) {

                jsoner_map_array = new Jsoner_map_array();
            }

            json_array.add(json_object);

            json_object = new JsonObject();

            return jsoner_map_array;
        }

        /**
         * <p>Is used to convert (String) json.
         * <p>Which was filled due to one or more number of calls to the
         * {@code _set(key, value)} method.
         */
        public String _to_json() {

            String result = gson.toJson(json_array);
            json_object = null;
            json_array = null;

            return result;
        }


    }


}