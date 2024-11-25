package lts;
import java.io.File;
import org.junit.Test;
import lts.Photo_manager.PM_format;


/** @see lts.Photo_manager */
public class Test_photo_manager {


    ////////// Method //////////
    @Test
    public void convert_png_to_jpeg() throws Exception {

        final String RESOURCES = "src/test/resources/Photo_manager/";
        File png = new File(RESOURCES + "input.png");
        File jpeg = new File(RESOURCES + "output.jpeg");

        Photo_manager._set_files(png, jpeg, PM_format.JPEG)
                     ._convert();

    }

    @Test
    public void convert_png_to_jpg() throws Exception {

        final String RESOURCES = "src/test/resources/Photo_manager/";
        File png = new File(RESOURCES + "input.png");
        File jpg = new File(RESOURCES + "output.jpg");

        Photo_manager._set_files(png, jpg, PM_format.JPG)
                     ._convert();

    }

    @Test
    public void convert_png_to_webp() throws Exception {

        final String RESOURCES = "src/test/resources/Photo_manager/";
        File png = new File(RESOURCES + "input.png");
        File webp = new File(RESOURCES + "output.webp");

        Photo_manager._set_files(png, webp, PM_format.WEBP)
                     ._convert();


    }


}