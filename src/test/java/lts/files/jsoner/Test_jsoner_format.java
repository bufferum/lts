package lts.files.jsoner;
import java.io.File;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import lts.Reporter_tests;
import lts.files.File_manager;
import lts.files.Jsoner;


/** @see lts.files.Jsoner */
public class Test_jsoner_format {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();
    private static final String RESOURCES = "src/test/resources/Test_jsoner/";


    ////////// Methods //////////
    @BeforeClass
    public static void delete_files_json() {

        try {

            File_manager._set_file(RESOURCES + "un_structuring.json", true)._delete();
            File_manager._set_file(RESOURCES + "un_destructuring.json", true)._delete();

        }
        catch(Exception e) { e.printStackTrace(); }

    }

    @Test public void structuring() throws Exception {

        String path = RESOURCES + "unknown.json";
        String result = Jsoner.FORMAT._structuring(new File(path));

        File_manager._set_file(RESOURCES + "un_structuring.json", true)
                    ._edit(result);

    }

    @Test public void unstructuring() throws Exception {

        String path = RESOURCES + "unknown.json";
        String result = Jsoner.FORMAT._unstructuring(new File(path));

        File_manager._set_file(RESOURCES + "un_destructuring.json", true)
                    ._edit(result);

    }


}