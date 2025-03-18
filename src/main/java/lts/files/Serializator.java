package lts.files;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;


/**
 * <h4>This class is designed to work with serializable classes.</h4>
 *
 * <p>Rules:
 * <ul>
 *  <li>You cannot save a class object(serialize) if it is not inherited
 *      from {@link java.io.Serializable}.
 *
 *  <li>It is better not to specify an extension for the file, this will
 *      increase security. But if necessary, then {@code .ser} is better.
 *
 *  <li>You cannot get static fields.
 *
 *  <li>If the class uses internal classes, then these internal classes
 *      must also inherit from {@link java.io.Serializable}.
 * </ul>
 *
 * @patterns Singleton
 * @version 2.0
 * @author bufferum
 */
@DefaultQualifier(NonNull.class)
public class Serializator {


    ////////// Constructors //////////
    private Serializator() { }


    ////////// Methods //////////
    /** Upload object - Saving an object to a file */
    public static <T extends Serializable> void _serialize(String path_to_file_upload, T object) throws Exception {

        FileOutputStream fileOutputStream = new FileOutputStream(new File(path_to_file_upload));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();

    }

    /** Upload object - Saving an object to a file */
    public static <T extends Serializable> void _serialize(File file_to_upload, T object) throws Exception {

        FileOutputStream fileOutputStream = new FileOutputStream(file_to_upload);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();

    }


    /** Download object - Getting an object from a file */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T _deserialize(String path_to_file_download) throws Exception {

        FileInputStream fileInputStream = new FileInputStream(new File(path_to_file_download));
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        T object = (T) objectInputStream.readObject();
        objectInputStream.close();

        return object;
    }

    /** Download object - Getting an object from a file */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T _deserialize(File file_to_download) throws Exception {

        FileInputStream fileInputStream = new FileInputStream(file_to_download);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        T object = (T) objectInputStream.readObject();
        objectInputStream.close();

        return object;
    }


}