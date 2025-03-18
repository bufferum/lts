package lts.files.jsoner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


/** @see lts.files.Jsoner */
@DefaultQualifier(NonNull.class)
public class Jsoner_format {


    ////////// Constructors //////////
    public Jsoner_format() { }


    ////////// Methods //////////
    /**
     * <p>The method converts the contents of a json file into a structured
     * view and returns the result as a String.
     */
    public String _structuring(String json) {

        return new GsonBuilder().setPrettyPrinting().create().toJson(JsonParser.parseString(json));
    }

    /**
     * <p>The method converts the contents of a json file into a structured
     * view and returns the result as a String.
     */
    public String _structuring(File file) {

        Reader reader = null;
        try {

            reader = new FileReader(file);

        }
        catch(FileNotFoundException e) { throw new RuntimeException(e); }
        JsonElement json_element = JsonParser.parseReader(reader);

        return new GsonBuilder().setPrettyPrinting().create().toJson(json_element);
    }

    /**
     * <p>The method converts the contents of a json file into a
     * unstructured (single line) form and returns the result as a string.
     */
    public String _unstructuring(String json) {

        return new Gson().toJson(JsonParser.parseString(json));
    }

    /**
     * <p>The method converts the contents of a json file into a
     * unstructured (single line) form and returns the result as a string.
     */
    public String _unstructuring(File file) {

        Reader reader = null;
        try {

            reader = new FileReader(file);

        }
        catch(FileNotFoundException e) { throw new RuntimeException(e); }
        JsonElement jsonElement = JsonParser.parseReader(reader);

        return new Gson().toJson(jsonElement);
    }


}