package lts;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;


/**
 * <p>This is a class for getting data from a file with
 * an extension {@code .properties}.
 *
 * <p>The format of the contents of these files is: {@code key=value}
 *
 * @version 1.0
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
        try {

            properties.load(new FileInputStream(this.file));

        }
        catch(FileNotFoundException e) { e.printStackTrace(); }
        catch(IOException e) { e.printStackTrace(); }

    }
    public Property_reader(String path_to_file) {

        file = new File(path_to_file);
        properties = new Properties();

        try {

            properties.load(new FileInputStream(file));

        }
        catch(FileNotFoundException e) { e.printStackTrace(); }
        catch(IOException e) { e.printStackTrace(); }

    }


    ////////// Methods //////////
    public String _get_value(String key) {

        return properties.getProperty(key);
    }

    public void _edit_value(String key, String value) throws Exception {

        properties.setProperty(key, value);
        properties.store(new FileOutputStream(file), null);

    }

    public synchronized Object _put(Object key, Object value) {

        return super.put(key, value);
    }

    public synchronized void _load(Reader reader) {

        try {

            super.load(reader);

        }
        catch(IOException e) { e.printStackTrace(); }

    }

    public synchronized void _load(InputStream inStream) {

        try {

            super.load(inStream);

        }
        catch(IOException e) { e.printStackTrace(); }

    }


}