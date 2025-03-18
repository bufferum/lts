package lts.files;
import java.io.File;
import org.junit.Rule;
import org.junit.Test;
import lts.Reporter_tests;
import lts.Test_USER;
import lts.signs.Print;


/** @see lts.files.File_manager */
public class Test_file_manager {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();
    private static final String RESOURCES = "src/test/resources/Test_file_manager/";


    ////////// Methods //////////
    @Test public void read_file() throws Exception {

        String result = "null";

        String text = Jsoner.CREATE._to_json(new Test_USER());

        File_manager._set_file(RESOURCES + "test_file.txt", true)
                    ._edit(text);

        result = File_manager._this_file()._read();

        Print.test(result);

    }

    @Test public void create_folder() {

        String result = "null";

        String text = Jsoner.CREATE._to_json(new Test_USER());

        try {

            File_manager._set_file(RESOURCES + "test_folder/test_file.txt", true)
                        ._edit(text);

            result = File_manager._this_file()._read();

        }
        catch(Exception e) { e.printStackTrace(); }

        Print.test(result);

    }

    @Test public void download() {

        String url = "https://google.com";
        File output_file = null;

        try {

            output_file = File_manager._set_file(RESOURCES + "download_from_google.txt", true)._get_file();

        }
        catch(Exception e) { e.printStackTrace(); }

        File_manager._download(url, output_file);

    }

    @Test public void copy() {

        File input_file = new File(RESOURCES + "test_file.txt");
        File output_file = new File(RESOURCES + "test_file_2.txt");
        File_manager._copy(input_file, output_file);

    }


}