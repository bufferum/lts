package lts.files;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;
import lts.signs.Print;


/**
 * <h4>Management for files.</h4>
 *
 * <p>Access to the methods lies through the method
 * <blockquote>
 *  {@code _set_file((File) file)} ->
 *  <ul>
 *      <li> {@code _create()}
 *      <li> {@code _edit()}
 *      <li> {@code _read()}
 *      <li> {@code _delete()}
 *  </ul>
 * </blockquote>
 *
 * @patterns Singleton, Memento, Builder
 * @version 2.0
 * @author bufferum
 */
@DefaultQualifier(NonNull.class)
public class File_manager {


    ////////// Variables //////////
    private static Allowed_file ALLOWED_FILE = new Allowed_file();
    @Nullable private static File_manager file_manager;
    @Nullable private File file;


    ////////// Constructors //////////
    private File_manager() { }


    ////////// Methods //////////
    /**
     * To work with a class, you must first add a file
     * @param last_name_is_file - This parameter determines the further
     *                                 behavior.
     *
     * <p>Example:
     * <p>A file with an absolute path is submitted - "/resource/File_manager/test"
     * <ul>
     *
     *  <li>If {@code last_name_is_file == true} - this means that the test
     *      directory is created inside the "/resource/File_manager/" directory and
     *      that's it.
     *
     *  <li>If {@code last_name_is_file == false} - this means that a test
     *      file is being created inside the "/resource/File_manager/" directory.
     *
     * </ul>
     *
     * @param path_to_file - the path to file that the operations will be performed on.
     */
    public static Allowed_file _set_file(String path_to_file, boolean last_name_is_file) throws Exception {

        return set_file(new File(path_to_file), last_name_is_file);
    }

    /**
     * To work with a class, you must first add a file
     * @param last_name_is_file - This parameter determines the further
     *                                 behavior.
     *
     * <p>Example:
     * <p>A file with an absolute path is submitted - "/resource/File_manager/test"
     * <ul>
     *
     *  <li>If {@code last_name_is_file == true} - this means that the test
     *      directory is created inside the "/resource/File_manager/" directory and
     *      that's it.
     *
     *  <li>If {@code last_name_is_file == false} - this means that a test
     *      file is being created inside the "/resource/File_manager/" directory.
     *
     * </ul>
     *
     * @param file - the file that the operations will be performed on.
     */
    public static Allowed_file _set_file(File file, boolean last_name_is_file) throws Exception {

        return set_file(file, last_name_is_file);
    }

    private static Allowed_file set_file(File file, boolean last_name_is_file) throws Exception {

        if(file_manager == null) {

            file_manager = new File_manager();

        }

        file_manager.file = file;

        // If last_name_is_file == false, then you need to create a file.
        if(last_name_is_file) {

            // Creating a file
            if(!file.exists()) {

                File parent_dir = file.getParentFile();

                if(parent_dir != null && !parent_dir.exists()) {

                    parent_dir.mkdirs(); // Creating all parent directories

                }

                file.createNewFile();

            }

        }

        // If last_name_is_file == true, then you need to create a directory.
        else {

            if(!file.exists()) {

                file.mkdirs(); // Creating a directory

            }

        }

        return ALLOWED_FILE;
    }

    /** Can get the file you are working with */
    public static File _get_file() throws Exception {

        if(file_manager.file == null) {

            file_manager = new File_manager();

            throw new Exception(Print.error("\n[File_manager_get_file] - The file has not been added\n"));
        }
        else {

            return file_manager.file;
        }

    }

    /** @return ALLOWED_FILE - This is access to the file change. */
    public static Allowed_file _this_file() {

        return ALLOWED_FILE;
    }

    /** Method for copying files */
    public static void _copy(File input_file, File copied_file) {

        if(input_file == null || copied_file == null) {

            throw new IllegalArgumentException("Files should not be null.");
        }

        Path source_path = input_file.toPath();
        Path destination_path = copied_file.toPath();

        try {

            Files.copy(source_path, destination_path, StandardCopyOption.REPLACE_EXISTING);

        }
        catch(IOException e) { e.printStackTrace(); }

    }

    /** This method is used to download any files from the specified URL to the specified file */
    public static void _download(URL url, String output_file_path) {

        download(url, new File(output_file_path));

    }

    /** This method is used to download any files from the specified URL to the specified file */
    public static void _download(URL url, File output_file) {

        download(url, output_file);

    }

    /** This method is used to download any files from the specified URL to the specified file */
    @SuppressWarnings("deprecation")
    public static void _download(String url, String output_file_path) {

        try {

            download(new URL(url), new File(output_file_path));

        }
        catch(MalformedURLException e) { e.printStackTrace(); }

    }

    /** This method is used to download any files from the specified URL to the specified file */
    @SuppressWarnings("deprecation")
    public static void _download(String url, File output_file) {

        try {

            download(new URL(url), output_file);

        }
        catch(MalformedURLException e) { e.printStackTrace(); }

    }

    private static void download(URL url, File output_file) {

        if(url == null) {

            throw new IllegalArgumentException("The URL must not be null!");
        }

        HttpURLConnection connection = null;

        try {

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

        }
        catch(IOException e) { e.printStackTrace(); }

        try(
            InputStream input_stream = connection.getInputStream();
            FileOutputStream output_stream = new FileOutputStream(output_file.getAbsolutePath());
           ) {

            byte[] buffer = new byte[4096];
            int bytes_read;

            while((bytes_read = input_stream.read(buffer)) != -1) {

                output_stream.write(buffer, 0, bytes_read);

            }

        }
        catch(Exception e) { e.printStackTrace(); }
        finally {

            connection.disconnect();

        }

    }


    ////////// Class //////////
    public static class Allowed_file {


        ////////// Constructors //////////
        public Allowed_file() { }


        ////////// Methods //////////
        public File _get_file() {

            return file_manager.file;
        }

        /** This method simultaneously modifies the text in the file, as well as saves it! */
        public Allowed_file _edit(Object object_or_text) throws Exception {

            FileWriter writer = new FileWriter(file_manager.file);
                       writer.write(String.valueOf(object_or_text));
                       writer.close();

            return this;
        }

        public String _read() throws Exception {

            StringBuilder content = new StringBuilder();

            BufferedReader reader = new BufferedReader(new FileReader(file_manager.file));
            String line = "";

            while((line = reader.readLine()) != null) {

                content.append(line).append(System.lineSeparator());

            }

            reader.close();

            return content.toString().trim();
        }

        public void _delete() throws Exception {

            file_manager.file.delete();

        }


    }


}