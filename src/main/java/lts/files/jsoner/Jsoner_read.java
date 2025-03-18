package lts.files.jsoner;
import java.io.File;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lts.files.File_manager;


/** @see lts.files.Jsoner */
@DefaultQualifier(NonNull.class)
public class Jsoner_read {


    ////////// Constructors //////////
    public Jsoner_read() { }


    ////////// Methods //////////
    /**
     * @param json - the text in Json format, inside which you need to read
     * the data
     */
    public Allowed_action _set_json(String json) {

        return new Allowed_action(json);
    }

    /**
     * @param json - the text in Json format, inside which you need to read
     * the data
     * @throws Exception
     */
    public Allowed_action _set_json(File file_json) throws Exception {

        return new Allowed_action(File_manager._set_file(file_json, true)._read());
    }


    ////////// Class //////////
    public static class Allowed_action {


        ////////// Variables //////////
        @Nullable private JsonObject json_object;


        ////////// Constructors //////////
        @SuppressWarnings("unused")
        private Allowed_action() { }
        public Allowed_action(String json) {

            json_object = JsonParser.parseString(json).getAsJsonObject();

        }


        ////////// Methods //////////
        /**
         * <p>This method can reading the {@code value} in the json root.
         *
         * <h4>ATTENTION!!!
         * <p>The method is not designed for depth. The method is designed
         * for {@code KEY=VALUE} inside the root of the json file.
         *
         * <pre>
         * {
         *     "name": "bufferum",
         *     "age": 20
         * }
         * </pre>
         *
         * <blockquote>
         * <p>You can reeading the {@code value} that have {@code keys}:
         * <ul>
         *     <li>name
         *     <li>age
         * </ul>
         * </blockquote>
         *
         * @param key - the key that will be used to search and reading the value
         */
        public String _value(String key) {

            return json_object.get(key).getAsString();
        }

        /**
         * <p>This method can change the value of JSONArray, using the name of the
         * array and the id of the desired cell of this array. <p>Example:
         *
         * <h4>ATTENTION!!!</h4>
         * <p>The method is not designed for depth. The method is designed
         * for {@code KEY=VALUE} inside the root of the json file.
         *
         * <pre>
         * {
         *     "character_traits": [
         *         "Kind",
         *         "Wit",
         *         "Brave"
         *      ]
         * }
         * </pre>
         *
         * <blockquote>
         * <p>You can reeading the {@code value} that have {@code keys}:
         * <ul>
         *     <li>character_traits[0]
         *     <li>character_traits[1]
         *     <li>character_traits[2]
         * </ul>
         * </blockquote>
         *
         * @param array_name - the name of the array to reading the value inside
         * @param i - id of the array cell for reading
         */
        public String _array(String array_name, int i) {

            JsonArray json_array = json_object.getAsJsonArray(array_name);
            return json_array.get(i).getAsString();
        }

        /**
         * <p>This method can read the value of a JSONObject, which is just
         * a JSONArray cell, using the array name and the ID of the desired
         * call to that array. <p>Example:
         *
         * <pre>
         * value_inner_array:
         *"studys": [
         *      {
         *          "educational_institution": "MGU_1",
         *          "year_of_completion": 2020
         *      },
         *      {
         *          "educational_institution": "MGU_2",
         *          "year_of_completion": 2024
         *      }
         * ]
         * </pre>
         *
         * <blockquote>
         * <p>You can reeading the {@code value} that have {@code keys}:
         * <ul>
         *     <li>educational_institution
         *     <li>year_of_completion
         * </ul>
         * </blockquote>
         *
         * <h4>ATTENTION!!!</h4>
         * <p>The method is not designed for depth. The method is designed
         * for {@code KEY=VALUE} inside the root of the json file.
         *
         * @param array_name - the name of the array to reading the value inside
         * @param i - id of the array cell for reading
         * @param key - the key that will be used to search and reading the value
         */
        public String _value_inner_array(String array_name, int i, String key) {

            JsonArray json_array = json_object.getAsJsonArray(array_name);
            JsonObject json_object_2 = json_array.get(i).getAsJsonObject();

            return json_object_2.get(key).getAsString();
        }


    }


}