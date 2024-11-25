package lts;
import java.io.File;
import org.junit.Test;


/** @see lts.File_manager */
public class Test_file_manager {


    ////////// Methods //////////
    @Test
    public void read_file() throws Exception {

        final String RESOURCES = "src/test/resources/File_manager/";
        String result = "null";

        String text = Jsoner.OBJECT._to_json(new User());

        File_manager._set_file(RESOURCES + "test_file.txt", true)
                    ._edit(text);

        result = File_manager._this_file()._read();

        Print.test(result);

    }

    @Test
    public void create_folder() {

        final String RESOURCES = "src/test/resources/File_manager/";
        String result = "null";

        String text = Jsoner.OBJECT._to_json(new User());

        try {

            File_manager._set_file(RESOURCES + "test_folder/test_file.txt", true)
                        ._edit(text);

            result = File_manager._this_file()._read();

        }
        catch(Exception e) { e.printStackTrace(); }

        Print.test(result);

    }

    @Test
    public void download() {

        final String RESOURCES = "src/test/resources/File_manager/";
        String url = "https://google.com";

        String prefix = Encrypter.SHA256._generate(String.valueOf(Random._random(-10000, 10000)._to_int())).substring(0, 10);
        File output_file = null;
        try {

            output_file = File_manager._set_file(RESOURCES + "download_google_" + prefix + "", true)._get_file();

        }
        catch(Exception e) { e.printStackTrace(); }

        File_manager._download(url, output_file);

    }

    @Test
    public void copy() {

        final String RESOURCES = "src/test/resources/File_manager/";

        File input_file = new File(RESOURCES + "test_file.txt");
        File output_file = new File(RESOURCES + "test_file_2.txt");
        File_manager._copy(input_file, output_file);

    }


}