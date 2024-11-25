package lts.Test_jsoner;
import java.util.Iterator;
import java.util.Map.Entry;
import org.junit.Test;

import lts.Jsoner;
import lts.Print;


/** @see lts.Jsoner */
public class Map {


    ////////// Methods //////////
    @Test
    public void MAP_to_json() throws Exception {

        String json = "null";

        json = Jsoner.MAP._set("name", "bufferum")
                         ._set("age", 20)
                         ._set("IQ", 2999.9999)
                         ._to_json();


        Print.result(json);

    }

    @Test
    public void MAP_from_json() throws Exception {

        // to json
        String json = "null";

        json = Jsoner.MAP._set("name", "bufferum")
                         ._set("age", 20)
                         ._set("IQ", 2999.9999)
                         ._to_json();

        Print.result("to_json:\n" + json + "\n\n");

        // from json
        Print.result("from_json:\n");
        Iterator<Entry<String, String>> iterator = Jsoner.MAP._from_json((String) json);

        while(iterator.hasNext()) {

            Entry<String, String> entry = iterator.next();

            Print.result(
                "key=" + entry.getKey() + " " +
                "value=" + entry.getValue() + "\n"
            );

        }

    }


}