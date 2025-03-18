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
public class Jsoner_update {


    ////////// Constructors //////////
    public Jsoner_update() { }


    ////////// Methods //////////
    /**
     * @param json - the text in Json format, inside which you need to read
     * the data.
     */
    public Allowed_action _set_json(String json) {

        return new Allowed_action(json);
    }

    /**
     * @param file_json - the file in Json format, inside which you need to read
     * the data.
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
         * <p>This method can change the {@code value} in the json root.
         *
         * <h4>ATTENTION!!!</h4>
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
         * <p>You can change the {@code value} that have {@code keys}:
         * <ul>
         *     <li>name
         *     <li>age
         * </ul>
         * </blockquote>
         *
         * @param key - the key that will be used to search and change the value
         * @param value - the new value to be set for the specified key
         */
        public Allowed_return_action _value(String key, Object value) {

            json_object.addProperty(key, String.valueOf(value));

            return new Allowed_return_action(json_object);
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
         * <p>You can change the {@code value} that have {@code keys}:
         * <ul>
         *     <li>character_traits[0]
         *     <li>character_traits[1]
         *     <li>character_traits[2]
         * </ul>
         * </blockquote>
         *
         * @param array_name - the name of the array to change the value inside
         * @param i - id of the array cell
         * @param value - the new value to be set for the specified key
         */
        public Allowed_return_action _array(String array_name, int i, Object value) {

            JsonArray json_array = json_object.getAsJsonArray(array_name);
                      json_array.set(i, JsonParser.parseString(String.valueOf(value)));

            return new Allowed_return_action(json_object);
        }

        /**
         * <p>This method can change the value of a JSONObject which is just a
         * cell of a JSONArray, using the name of the array and the id of the
         * desired call of this array. <p>Example:
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
         * <p>You can change the {@code value} that have {@code keys}:
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
         * @param array_name - the name of the array to change the value inside
         * @param i - id of the array cell
         * @param key - the key that will be used to search and change the value
         * @param value - the new value to be set for the specified key
         */
        public Allowed_return_action _value_inner_array(String array_name, int i, String key, Object value) {

            JsonArray json_array = json_object.getAsJsonArray(array_name);
            JsonObject json_object_2 = json_array.get(i).getAsJsonObject();
                       json_object_2.addProperty(key, String.valueOf(value));

            return new Allowed_return_action(json_object);
        }


        ////////// Class //////////
        public static class Allowed_return_action {


            ////////// Variables //////////
            @Nullable private JsonObject json_object;


            ////////// Constructors //////////
            public Allowed_return_action(JsonObject json_object) {

                this.json_object = json_object;

            }


            ////////// Methods //////////
            public String _get_json() {

                return json_object.toString();
            }

            public void _save_to_file(String path_to_file) throws Exception {

                File_manager._set_file(new File(path_to_file), true)
                            ._edit(json_object.toString());

            }

            public void _save_to_file(File file_json) throws Exception {

                File_manager._set_file(file_json, true)
                            ._edit(json_object.toString());

            }


        }


    }


}