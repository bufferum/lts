package lts.files;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;
import lts.files.Photo_manager;


/**
 * <h4>Manager for photos. A class for converting photos to a webp or jpeg extension.</h4>
 * <p>Use such a record:
 *
 * <pre>
 * Photo_manager._set_files(png, webp, PM_format.WEBP)
 *              ._convert();
 * </pre>
 *
 * @patterns Singleton, Builder
 * @version 2.0
 * @author bufferum
 */
@DefaultQualifier(NonNull.class)
public class Photo_manager {


    ////////// Variables //////////
    @Nullable private static Photo_manager pm;
    @Nullable private static Photo_manager_allowed pm_allowed;
    @Nullable private File input_file;
    @Nullable private File output_file;
    @Nullable private PM_format pm_format;


    ////////// Constructors //////////
    private Photo_manager() { }


    ////////// Enums //////////
    public static enum PM_format {


        /** The value for converting the input file to the JPEG extension */
        JPEG("jpeg"),

        /** The value for converting the input file to the JPG extension */
        JPG("jpg"),

        /** The value for converting the input file to the WEBP extension */
        WEBP("webp");


        private String format;


        PM_format(String format) {

            this.format = format;

        }


    }


    ////////// Methods //////////
    public static Photo_manager_allowed _set_files(String path_to_input_file, String path_to_output_file, PM_format pm_format) {

        if(pm == null) {

            pm = new Photo_manager();
            pm_allowed = new Photo_manager_allowed();

        }

        pm.input_file = new File(path_to_input_file);
        pm.output_file = new File(path_to_output_file);
        pm.pm_format = pm_format;

        return pm_allowed;
    }
    public static Photo_manager_allowed _set_files(File input_file, File output_file, PM_format pm_format) {

        if(pm == null) {

            pm = new Photo_manager();
            pm_allowed = new Photo_manager_allowed();

        }

        pm.input_file = input_file;
        pm.output_file = output_file;
        pm.pm_format = pm_format;

        return pm_allowed;
    }


    ////////// Class //////////
    public static class Photo_manager_allowed {


        ////////// Constructors //////////
        private Photo_manager_allowed() { }


        ////////// Methods //////////
        public void _convert() throws Exception {


            ////////// Variables //////////
            BufferedImage image;
            BufferedImage converted_image;
            Graphics2D graphics2d;
            ImageOutputStream ios;
            ImageWriter writer;
            IIOImage image_IO;
            ImageWriteParam param;


            // Formation of the input file
            image = ImageIO.read(pm.input_file);
            converted_image = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_INT_RGB
            );

            graphics2d = converted_image.createGraphics();
            graphics2d.drawImage(image, 0, 0, null);


            // Formation of the output file
            ios = ImageIO.createImageOutputStream(pm.output_file);
            writer = ImageIO.getImageWritersByFormatName(pm.pm_format.format).next();
            writer.setOutput(ios);

            image_IO = new IIOImage(converted_image, null, null);
            param = writer.getDefaultWriteParam();


            // Creating a new image
            writer.write(null, image_IO, param);


            // Closure to avoid leaks
            writer.dispose();
            ios.close();
            graphics2d.dispose();


        }


    }


}