package lts.jsoner;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/** @see lts.Jsoner */
@DefaultQualifier(NonNull.class)
public class Jsoner_object  {


    ////////// Variables //////////
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    ////////// Constructors //////////
    public Jsoner_object() { }


    ////////// Methods //////////
    /** To convert data into a json (String) structure */
    public String _to_json(Object object) {

        return gson.toJson(object);
    }

    /** To convert a json structure into a collection for further iteration */
    public <T> T _from_json(String json, Class<T> class_of_T) {

        return gson.fromJson(json, class_of_T);
    }


}