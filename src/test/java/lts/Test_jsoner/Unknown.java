package lts.Test_jsoner;
import java.io.File;
import org.junit.Test;

import lts.File_manager;
import lts.Jsoner;


/** @see lts.Jsoner */
public class Unknown {


    ////////// Methods //////////
    @Test
    public void UNKNOWN_structuring() throws Exception {

        final String RESOURCES = "src/test/resources/Jsoner/";
        String path = RESOURCES + "unknown.json";
        String result = Jsoner.FORMAT._structuring(new File(path));

        File_manager._set_file(new File(RESOURCES + "un_structuring.json"), true)
                    ._edit(result);


    }

    @Test
    public void UNKNOWN_unstructuring() throws Exception {

        final String RESOURCES = "src/test/resources/Jsoner/";
        String path = RESOURCES + "unknown.json";
        String result = Jsoner.FORMAT._unstructuring(new File(path));

        File_manager._set_file(new File(RESOURCES + "un_destructuring.json"), true)
                    ._edit(result);

    }


}