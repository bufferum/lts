package lts.files;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;


/**
 * <h4>This is a class for getting data from a file with
 * an extension {@code .properties}.</h4>
 *
 * <p>The format of the contents of these files is: {@code key=value}
 *
 * @version 2.0
 * @author bufferum
 */
@DefaultQualifier(NonNull.class)
public class Property_reader extends Properties {


    ////////// Variables //////////
    @Nullable private Properties properties;
    @Nullable private File file;


    ////////// Constructors //////////
    public Property_reader(File file) {

        this.file = file;
        properties = new Properties();

        load_properties();

    }
    public Property_reader(String path_to_file) {

        this.file = new File(path_to_file);
        properties = new Properties();

        load_properties();

    }


    ////////// Methods //////////
    private void load_properties() {

        try {

            properties.load(new FileInputStream(this.file));

        }
        catch(FileNotFoundException e) { e.printStackTrace(); }
        catch(IOException e) { e.printStackTrace(); }

    }

    /** If the reader is already running, but at some point the file .properties has been edited manually and the reader needs to be restarted. */
    public void reload() {

        load_properties();

    }

    /** To read the property value */
    public String _get_value(String key) {

        return properties.getProperty(key);
    }

    /** To edit the property value */
    public void _edit_value(String key, String value) throws Exception {

        properties.setProperty(key, value);
        properties.store(new FileOutputStream(file), null);

    }

    /** To add a property */
    public synchronized Object _add(Object key, Object value) {

        return super.put(key, value);
    }


}